package com.AiFunding.ToBi.dto.auth;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DuplicateDto {
    boolean isExist;
    DuplicateDto (boolean isExist){
        this.isExist = isExist;
    }
}
