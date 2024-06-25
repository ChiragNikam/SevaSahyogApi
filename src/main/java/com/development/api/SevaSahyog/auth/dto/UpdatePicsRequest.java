package com.development.api.SevaSahyog.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdatePicsRequest {
    private String profilePicUrl;
    private String backgroundPicUrl;
}
