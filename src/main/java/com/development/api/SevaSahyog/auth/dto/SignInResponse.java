package com.development.api.SevaSahyog.auth.dto;

import com.development.api.SevaSahyog.auth.data.NgoAccount;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignInResponse {

    private String token;
    private NgoAccount ngoAccount;

}
