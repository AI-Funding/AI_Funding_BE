package com.AiFunding.ToBi.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoogleDto {

    private String id;

    private String email;

    private Boolean verified_email;

    private String name;

    private String given_name;

    private String family_name;

    private String picture;

    private String local;
}
