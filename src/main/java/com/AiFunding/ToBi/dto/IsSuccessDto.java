package com.AiFunding.ToBi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class IsSuccessDto {
    private Boolean success;

    public IsSuccessDto(final Boolean success) {
        this.success = success;
    }
}
