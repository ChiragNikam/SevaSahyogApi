package com.development.api.SevaSahyog.auth.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNgoName() {
        return ngoName;
    }

    public void setNgoName(String ngoName) {
        this.ngoName = ngoName;
    }

    public String getNgoImage() {
        return ngoImage;
    }

    public void setNgoImage(String ngoImage) {
        this.ngoImage = ngoImage;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAboutNgo() {
        return aboutNgo;
    }

    public void setAboutNgo(String aboutNgo) {
        this.aboutNgo = aboutNgo;
    }
}
