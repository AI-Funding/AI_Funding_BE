package com.AiFunding.ToBi.dto.Tab.History;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HistoryRequestDto {

    @JsonProperty("customer_info_id")
    private Long id;

    @JsonProperty("login_type")
    private String loginType;

    public HistoryRequestDto(final Long id, final String loginType) {
        this.id = id;
        this.loginType = loginType;
    }
}
