package com.AiFunding.ToBi.service.ai.history;

import com.AiFunding.ToBi.dto.ai.History.HistoryListResponseDto;
import com.AiFunding.ToBi.dto.ai.History.HistoryResponseDto;
import com.AiFunding.ToBi.entity.AccountEntity;
import com.AiFunding.ToBi.entity.CustomerInformationEntity;
import com.AiFunding.ToBi.entity.TradingDetailEntity;
import com.AiFunding.ToBi.mapper.CustomerInformationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryService {

    private final CustomerInformationRepository customerInformationRepository;

    public HistoryService(CustomerInformationRepository customerInformationRepository){
        this.customerInformationRepository = customerInformationRepository;
    }

    // User 정보를 찾고 거래 내역 DTO 를 반환
    public HistoryResponseDto findUserHistory(final Long id, final String loginType) {
        CustomerInformationEntity customerInfo = customerInformationRepository.findByIdAndLoginType(id, loginType);

        return new HistoryResponseDto(getHistoryDtoList(customerInfo.getAccounts()));
    }

    // 거래내역 관련 데이터 리스트 반환
    public List<HistoryListResponseDto> getHistoryDtoList(List<AccountEntity> accounts){
        List<HistoryListResponseDto> historyDtoList = new ArrayList<>();

        for(AccountEntity account : accounts){

            for(TradingDetailEntity trading : account.getTradingEntities()){
                String tradeType = trading.getTradingType();                //거래 종류
                Integer tradingAmount = trading.getTradingAmount();         //거래 수량
                Long tradingPrice = trading.getTradingPrice();              //매수/도 가
                Long totalPrice = trading.getTradingPrice();                //총 거래 금액
                Long currentPrice = totalPrice / (long)tradingAmount;       //단가, 1주당 가격

                historyDtoList.add(new HistoryListResponseDto(trading.getStock().getItemName(), trading.getCreateAt(),
                        totalPrice, tradeType, tradingAmount, currentPrice, tradingPrice));
            }

        }
        
        Collections.sort(historyDtoList, Comparator.comparing(HistoryListResponseDto::getTradeDate));   //시간 순 정렬
        return historyDtoList;
    }
}
