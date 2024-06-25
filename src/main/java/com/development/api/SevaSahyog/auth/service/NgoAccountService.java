package com.development.api.SevaSahyog.auth.service;

import com.development.api.SevaSahyog.auth.data.NgoAccount;
import com.development.api.SevaSahyog.auth.dto.UpdateNgoAccountRequest;
import com.development.api.SevaSahyog.auth.dto.UpdatePicsRequest;

public interface NgoAccountService {

    NgoAccount updateNgoAccount(UpdateNgoAccountRequest updatedData, String userId);

    NgoAccount updateNgoAccountPics(String userId, UpdatePicsRequest updatePicsRequest);
}
