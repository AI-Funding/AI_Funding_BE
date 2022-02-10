package com.AiFunding.ToBi.service.comparator;

import com.AiFunding.ToBi.dto.Home.StockListResponseDto;

import java.util.Comparator;

public class StockComparator implements Comparator<StockListResponseDto> {

    @Override
    public int compare(StockListResponseDto o1, StockListResponseDto o2) {
        if (o1.getPercent_by_account() < o2.getPercent_by_account()) return 1;
        else if (o1.getPercent_by_account() > o2.getPercent_by_account()) return -1;
        return 0;
    }
}
