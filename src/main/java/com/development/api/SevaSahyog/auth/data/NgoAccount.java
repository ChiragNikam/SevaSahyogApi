package com.development.api.SevaSahyog.auth.data;

import com.development.api.SevaSahyog.events.data.Event;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Setter
@Getter
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

    public String getUserName(){
        return userName;
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