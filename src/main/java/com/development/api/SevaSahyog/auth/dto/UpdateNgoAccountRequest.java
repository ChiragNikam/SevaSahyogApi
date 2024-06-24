package com.development.api.SevaSahyog.auth.dto;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getNgoName() {
        return ngoName;
    }

    public void setNgoName(String ngoName) {
        this.ngoName = ngoName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
