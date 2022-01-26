package com.AiFunding.ToBi.service;

import com.AiFunding.ToBi.dto.Home.AccountListResponseDto;
import com.AiFunding.ToBi.dto.Home.StockListResponseDto;
import com.AiFunding.ToBi.dto.Home.UserResponseDto;
import com.AiFunding.ToBi.entity.AccountEntity;
import com.AiFunding.ToBi.entity.AccountStockDetailEntity;
import com.AiFunding.ToBi.entity.CustomerInformationEntity;
import com.AiFunding.ToBi.entity.StockEntity;
import com.AiFunding.ToBi.mapper.CustomerInformationRepository;
import com.AiFunding.ToBi.mapper.StockRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class HomeService {

    private final CustomerInformationRepository customerInformationRepository;
    private final StockRepository stockRepository;

    public HomeService(CustomerInformationRepository customerInformationRepository, StockRepository stockRepository) {
        this.customerInformationRepository = customerInformationRepository;
        this.stockRepository = stockRepository;
    }

    public UserResponseDto findUserInfo(final Long userSequence, final String loginType){
        CustomerInformationEntity customer = customerInformationRepository.findByUserSequenceAndLoginType(userSequence,loginType);

        return new UserResponseDto(customer.getNickname(),getAccountData(customer.getAccounts()));

    }

    public List<AccountListResponseDto> getAccountData(List<AccountEntity> accounts){
        List<AccountListResponseDto> accountData = new ArrayList<>();

        // TODO: total, today 수익률 구하는 공식 생각해보기
        for(AccountEntity entity : accounts){
            accountData.add(new AccountListResponseDto("닉네임",entity.getBalance()
            ,entity.getCreateAt(),30.3,10000,20.2,entity.getIncome(),
                    getStockData(entity)));
        }

        Collections.sort(accountData, new AccountComparator());
        return accountData;
    }

    public List<StockListResponseDto> getStockData(AccountEntity account){
        List<StockListResponseDto> stockList = new ArrayList<>();

        Integer balance = 0;

        for(AccountStockDetailEntity stocks : account.getAccountStocks()){
            balance += stocks.getAveragePrice() * stocks.getStockAmount();
        }

        for(AccountStockDetailEntity stocks : account.getAccountStocks()){
            StockEntity stockEntity = stockRepository.findById(stocks.getItemId()).get();
            stockList.add(new StockListResponseDto(stockEntity.getItemName(),
                    stockEntity.getNowPrice(),stocks.getIncome(),
                    (double) (stocks.getStockAmount()*stocks.getAveragePrice()/balance*100)));
        }
        Collections.sort(stockList,new StockComparator()); // 점유율에 대해서 내림차순으로 정렬
        return stockList;
    }
}

class StockComparator implements Comparator<StockListResponseDto>{

    @Override
    public int compare(StockListResponseDto o1, StockListResponseDto o2) {
        if(o1.getPercent_by_account() < o2.getPercent_by_account()) return 1;
        else if(o1.getPercent_by_account() > o2.getPercent_by_account()) return -1;
        return 0;
    }
}

class AccountComparator implements Comparator<AccountListResponseDto>{

    @Override
    public int compare(AccountListResponseDto o1, AccountListResponseDto o2) {
        return o1.getCreateAt().compareTo(o2.getCreateAt());
    }
}
