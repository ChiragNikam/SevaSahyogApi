package com.development.api.SevaSahyog.auth.service.jwtimpl;

import com.development.api.SevaSahyog.auth.config.JwtAuthFilter;
import com.development.api.SevaSahyog.auth.data.NgoAccount;
import com.development.api.SevaSahyog.auth.dto.SignInRequest;
import com.development.api.SevaSahyog.auth.dto.SignUpRequest;
import com.development.api.SevaSahyog.auth.repo.NgoAccountRepo;
import com.development.api.SevaSahyog.auth.service.AuthService;
import com.development.api.SevaSahyog.auth.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final NgoAccountRepo ngoAccountRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public String signIn(SignInRequest signInRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));

        NgoAccount account = ngoAccountRepo.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        return jwtService.generateToken(account);
    }

    public NgoAccount signUp(SignUpRequest signUpRequest){
        NgoAccount account = new NgoAccount();

        account.setProfileImage(signUpRequest.getProfileImage());
        account.setUserName(signUpRequest.getUserName());
        account.setMobileNo(signUpRequest.getMobileNo());
        account.setEmail(signUpRequest.getEmail());
        account.setNgoImage(signUpRequest.getNgoImage());
        account.setNgoName(signUpRequest.getNgoName());
        account.setLocation(signUpRequest.getLocation());
        account.setAboutNgo(signUpRequest.getAboutNgo());
        account.setLongDesc(signUpRequest.getLongDesc());
        account.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        return ngoAccountRepo.save(account);
    }
}
