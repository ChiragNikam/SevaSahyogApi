package com.development.api.SevaSahyog.auth.controller;

import com.development.api.SevaSahyog.auth.data.NgoAccount;
import com.development.api.SevaSahyog.auth.dto.SignInRequest;
import com.development.api.SevaSahyog.auth.dto.SignUpRequest;
import com.development.api.SevaSahyog.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/admin")
@RequiredArgsConstructor
public class Authentication {

    private final AuthService authService;

    @PostMapping(path = "/signIn")
    public ResponseEntity<?> login(@RequestBody SignInRequest signInRequest){
        String token = "";
        try{
            token = authService.signIn(signInRequest);
        } catch (Exception e){
            return ResponseEntity.ok(e.getLocalizedMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PostMapping(path = "/signUp")
    public ResponseEntity<?> register(@RequestBody SignUpRequest signUpRequest){
        NgoAccount account = null;
        try{
            account = authService.signUp(signUpRequest);
        } catch (Exception e){
            return ResponseEntity.ok(e.getLocalizedMessage());
        }
        return ResponseEntity.ok(account);
    }
}
