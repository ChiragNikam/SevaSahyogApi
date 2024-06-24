package com.development.api.SevaSahyog.auth.data;

import com.development.api.SevaSahyog.events.data.Event;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "ngo_accounts")
public class NgoAccount implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;

    // user details
    private String profileImage;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false, unique = true)
    private String mobileNo;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    // users ngo details
    private String ngoImage;
    @Column(nullable = false)
    private String ngoName;
    private String location;
    private String aboutNgo;
    private String longDesc;
    //event mapping
    @OneToMany
    private List<Event> event;

    // getters and setters
    public String getAboutNgo() {
        return aboutNgo;
    }

    public List<Event> getEvent() {
        return event;
    }

    public void setEvent(List<Event> event) {
        this.event = event;
    }

    public void setAboutNgo(String aboutNgo) {
        this.aboutNgo = aboutNgo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getNgoImage() {
        return ngoImage;
    }

    public void setNgoImage(String ngoImage) {
        this.ngoImage = ngoImage;
    }

    public String getNgoName() {
        return ngoName;
    }

    public void setNgoName(String ngoName) {
        this.ngoName = ngoName;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}