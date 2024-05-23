package com.development.api.SevaSahyog.auth.service;

import com.development.api.SevaSahyog.auth.data.NgoAccount;
import com.development.api.SevaSahyog.auth.dto.SignInRequest;
import com.development.api.SevaSahyog.auth.dto.SignInResponse;
import com.development.api.SevaSahyog.auth.dto.SignUpRequest;

public interface AuthService {
    SignInResponse signIn(SignInRequest signInRequest);

    NgoAccount signUp(SignUpRequest signUpRequest);
}
