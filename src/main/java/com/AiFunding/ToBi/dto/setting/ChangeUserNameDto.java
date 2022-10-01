package com.AiFunding.ToBi.dto.setting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeUserNameDto {

    @JsonProperty("customer_info_id")
    private Long customerInfoId;

    @JsonProperty("login_type")
    private String loginType;

    @JsonProperty("current_name")
    private String currentName;

    @JsonProperty("changed_name")
    private String changedName;

}
