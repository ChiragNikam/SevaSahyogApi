package com.development.api.SevaSahyog.auth.repo;

import com.development.api.SevaSahyog.auth.data.NgoAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NgoAccountRepo extends JpaRepository<NgoAccount, String> {

    Optional<NgoAccount> findByEmail(String email);

}
