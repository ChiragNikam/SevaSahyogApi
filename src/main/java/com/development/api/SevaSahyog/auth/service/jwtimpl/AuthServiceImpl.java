package com.development.api.SevaSahyog.auth.service.jwtimpl;

import com.development.api.SevaSahyog.auth.data.NgoAccount;
import com.development.api.SevaSahyog.auth.dto.SignInRequest;
import com.development.api.SevaSahyog.auth.dto.SignInResponse;
import com.development.api.SevaSahyog.auth.dto.SignUpRequest;
import com.development.api.SevaSahyog.auth.dto.UpdateNgoAccountRequest;
import com.development.api.SevaSahyog.auth.repo.NgoAccountRepo;
import com.development.api.SevaSahyog.auth.service.AuthService;
import com.development.api.SevaSahyog.auth.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final NgoAccountRepo ngoAccountRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public NgoAccount signUp(SignUpRequest signUpRequest) {
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

    @Override
    public SignInResponse signIn(SignInRequest signInRequest) {

        if (SecurityContextHolder.getContext().getAuthentication() == null)
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));

        NgoAccount account = ngoAccountRepo.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        if (!passwordEncoder.matches(signInRequest.getPassword(), account.getPassword())){
            throw new IllegalArgumentException("Invalid password");
        }
        SignInResponse response = new SignInResponse();
        response.setToken(jwtService.generateToken(account));
        response.setNgoAccount(account);

        return response;
    }

    @Override
    public NgoAccount updateNgoAccount(UpdateNgoAccountRequest updatedData, String userId) {
        Optional<NgoAccount> oldAccountDataOpt = ngoAccountRepo.findById(userId);
        NgoAccount existingNgoAccount = null;
        if (oldAccountDataOpt.isPresent()){
            existingNgoAccount = oldAccountDataOpt.get();

            existingNgoAccount.setUserName(updatedData.getUserName());
            existingNgoAccount.setMobileNo(updatedData.getMobileNo());
            existingNgoAccount.setEmail(updatedData.getEmail());
            existingNgoAccount.setNgoName(updatedData.getNgoName());
            existingNgoAccount.setLocation(updatedData.getLocation());
            existingNgoAccount.setAboutNgo(updatedData.getAboutNgo());
            existingNgoAccount.setLongDesc(updatedData.getLongDesc());

            return ngoAccountRepo.save(existingNgoAccount);
        }else {
            throw new IllegalArgumentException("Unable to find User");
        }
    }
}
