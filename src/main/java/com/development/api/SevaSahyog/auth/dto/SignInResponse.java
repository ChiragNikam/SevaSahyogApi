package com.development.api.SevaSahyog.auth.dto;

import com.development.api.SevaSahyog.auth.data.NgoAccount;

public class SignInResponse {

    private String token;
    private NgoAccount ngoAccount;

    public NgoAccount getNgoAccount() {
        return ngoAccount;
    }

    public void setNgoAccount(NgoAccount ngoAccount) {
        this.ngoAccount = ngoAccount;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
