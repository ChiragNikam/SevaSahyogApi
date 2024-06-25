package com.development.api.SevaSahyog.auth.service.jwtimpl;

import com.development.api.SevaSahyog.auth.data.NgoAccount;
import com.development.api.SevaSahyog.auth.dto.UpdateNgoAccountRequest;
import com.development.api.SevaSahyog.auth.dto.UpdatePicsRequest;
import com.development.api.SevaSahyog.auth.repo.NgoAccountRepo;
import com.development.api.SevaSahyog.auth.service.NgoAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NgoAccountServiceImpl implements NgoAccountService {

    private final NgoAccountRepo ngoAccountRepo;

    @Override
    public NgoAccount updateNgoAccount(UpdateNgoAccountRequest updatedData, String userId) {
        Optional<NgoAccount> oldAccountDataOpt = ngoAccountRepo.findById(userId);
        NgoAccount existingNgoAccount = null;
        if (oldAccountDataOpt.isPresent()){
            existingNgoAccount = oldAccountDataOpt.get();

            existingNgoAccount.setUserName(updatedData.getUserName());
            existingNgoAccount.setMobileNo(updatedData.getMobileNo());
            existingNgoAccount.setEmail(updatedData.getEmail());
            existingNgoAccount.setNgoName(updatedData.getNgoName());
            existingNgoAccount.setLocation(updatedData.getLocation());
            existingNgoAccount.setAboutNgo(updatedData.getAboutNgo());
            existingNgoAccount.setLongDesc(updatedData.getLongDesc());

            return ngoAccountRepo.save(existingNgoAccount);
        }else {
            throw new IllegalArgumentException("Unable to find User");
        }
    }

    @Override
    public NgoAccount updateNgoAccountPics(String userId, UpdatePicsRequest updatePicsRequest) {
        Optional<NgoAccount> oldAccountOpt = ngoAccountRepo.findById(userId);
        NgoAccount existingNgoAccount = null;
        if (oldAccountOpt.isPresent()){
            existingNgoAccount = oldAccountOpt.get();

            if (updatePicsRequest.getProfilePicUrl() != null) existingNgoAccount.setProfileImage(updatePicsRequest.getProfilePicUrl());
            if (updatePicsRequest.getBackgroundPicUrl() != null)existingNgoAccount.setNgoImage(updatePicsRequest.getBackgroundPicUrl());

            return ngoAccountRepo.save(existingNgoAccount);
        }else {
            throw new IllegalArgumentException("Unable to find User");
        }
    }
}
