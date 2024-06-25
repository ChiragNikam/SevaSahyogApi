package com.development.api.SevaSahyog.auth.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
public class SignUpRequest {
    // user details
    private String profileImage;
    private String userName;
    private String mobileNo;
    private String email;
    private String password;

    // users ngo details
    private String ngoImage;
    private String ngoName;
    private String location;
    private String aboutNgo;
    private String longDesc;

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNgoName(String ngoName) {
        this.ngoName = ngoName;
    }

    public void setNgoImage(String ngoImage) {
        this.ngoImage = ngoImage;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAboutNgo(String aboutNgo) {
        this.aboutNgo = aboutNgo;
    }
}
