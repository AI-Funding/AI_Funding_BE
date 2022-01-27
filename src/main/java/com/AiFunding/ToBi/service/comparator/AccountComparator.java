package com.AiFunding.ToBi.service.comparator;

import com.AiFunding.ToBi.dto.Home.AccountListResponseDto;

import java.util.Comparator;

public class AccountComparator implements Comparator<AccountListResponseDto>{

    @Override
    public int compare(AccountListResponseDto o1, AccountListResponseDto o2) {
        return o1.getCreateAt().compareTo(o2.getCreateAt());
    }
}
