package com.development.api.SevaSahyog.auth.service;

import com.development.api.SevaSahyog.auth.data.NgoAccount;
import com.development.api.SevaSahyog.auth.dto.SignInRequest;
import com.development.api.SevaSahyog.auth.dto.SignUpRequest;

public interface AuthService {
    String signIn(SignInRequest signInRequest);

    NgoAccount signUp(SignUpRequest signUpRequest);
}
