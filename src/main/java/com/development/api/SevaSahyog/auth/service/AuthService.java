package com.development.api.SevaSahyog.auth.service;

import com.development.api.SevaSahyog.auth.data.NgoAccount;
import com.development.api.SevaSahyog.auth.dto.SignInRequest;
import com.development.api.SevaSahyog.auth.dto.SignInResponse;
import com.development.api.SevaSahyog.auth.dto.SignUpRequest;
import com.development.api.SevaSahyog.auth.dto.UpdateNgoAccountRequest;

public interface AuthService {
    SignInResponse signIn(SignInRequest signInRequest);

    NgoAccount signUp(SignUpRequest signUpRequest);

    NgoAccount updateNgoAccount(UpdateNgoAccountRequest updatedData, String userId);
}
