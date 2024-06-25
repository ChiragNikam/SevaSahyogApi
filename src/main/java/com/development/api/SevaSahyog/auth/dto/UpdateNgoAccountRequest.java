package com.development.api.SevaSahyog.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateNgoAccountRequest {
    // user details
    private String userName;
    private String mobileNo;
    private String email;

    // users ngo details
    private String ngoName;
    private String location;
    private String aboutNgo;
    private String longDesc;

}
