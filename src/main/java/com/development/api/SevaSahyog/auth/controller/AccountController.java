package com.development.api.SevaSahyog.auth.controller;

import com.development.api.SevaSahyog.auth.data.NgoAccount;
import com.development.api.SevaSahyog.auth.dto.ErrorResponse;
import com.development.api.SevaSahyog.auth.dto.UpdateNgoAccountRequest;
import com.development.api.SevaSahyog.auth.dto.UpdatePicsRequest;
import com.development.api.SevaSahyog.auth.repo.NgoAccountRepo;
import com.development.api.SevaSahyog.auth.service.NgoAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("account/")
public class AccountController {

    public final NgoAccountRepo ngoAccountRepo;
    public final NgoAccountService ngoAccountService;

    @GetMapping("/ngo/{userId}")
    public ResponseEntity<?> getNgoAccount(@PathVariable String userId) {   // get ngo account by its userId
        Optional<NgoAccount> accountData = Optional.empty();
        try {
            accountData = ngoAccountRepo.findById(userId);
        } catch (Exception e) {
            ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(HttpStatus.CONFLICT.value(), e.getLocalizedMessage()));
        }

        if (accountData.isPresent())
            return ResponseEntity.ok(accountData);
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(HttpStatus.CONFLICT.value(), "Account not found"));
    }

    @PutMapping("/ngo/{userId}")
    public ResponseEntity<?> updateNgoAccount(@RequestBody UpdateNgoAccountRequest updatedNgoAccount, @PathVariable String userId) {
        try {
            NgoAccount updatedAccount = ngoAccountService.updateNgoAccount(updatedNgoAccount, userId);
            return ResponseEntity.ok(updatedAccount);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(HttpStatus.CONFLICT.value(), e.getLocalizedMessage()));
        }
    }

    @PutMapping("/ngo/updateImage/{userId}")
    public ResponseEntity<?> updateProfileAndBackgroundPic(@PathVariable String userId, @RequestBody UpdatePicsRequest updatePicsRequest){
        try {
            NgoAccount updatedAccount = ngoAccountService.updateNgoAccountPics(userId, updatePicsRequest);
            return ResponseEntity.ok(updatedAccount);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(HttpStatus.CONFLICT.value(), e.getLocalizedMessage()));
        }
    }
}