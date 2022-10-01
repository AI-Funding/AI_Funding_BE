package com.AiFunding.ToBi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class IsSuccessDto {
    private Boolean isSuccess;

    public IsSuccessDto(final Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
