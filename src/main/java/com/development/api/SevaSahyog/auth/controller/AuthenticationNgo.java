package com.development.api.SevaSahyog.auth.controller;

import com.development.api.SevaSahyog.auth.data.NgoAccount;
import com.development.api.SevaSahyog.auth.dto.SignInRequest;
import com.development.api.SevaSahyog.auth.dto.SignInResponse;
import com.development.api.SevaSahyog.auth.dto.SignUpRequest;
import com.development.api.SevaSahyog.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/ngo")
@RequiredArgsConstructor
public class AuthenticationNgo {

    private final AuthService authService;

    @PostMapping(path = "/signIn")
    public ResponseEntity<?> login(@RequestBody SignInRequest signInRequest){
        SignInResponse response = null;
        try{
            response = authService.signIn(signInRequest);
        } catch (Exception e){
            return ResponseEntity.ok(e.getLocalizedMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(path = "/signUp")
    public ResponseEntity<?> register(@RequestBody SignUpRequest signUpRequest){
        NgoAccount account = null;
        try{
            account = authService.signUp(signUpRequest);
        } catch (DataIntegrityViolationException dataException){
            return ResponseEntity.ok("Correct your data");
        } catch (Exception e){
            return ResponseEntity.ok(e.getClass());
        }
        return ResponseEntity.ok(account);
    }
}
