package com.AiFunding.ToBi.service.home;

import com.AiFunding.ToBi.dto.Home.AccountListResponseDto;
import com.AiFunding.ToBi.dto.Home.StockListResponseDto;
import com.AiFunding.ToBi.dto.Home.UserResponseDto;
import com.AiFunding.ToBi.entity.AccountEntity;
import com.AiFunding.ToBi.entity.AccountStockDetailEntity;
import com.AiFunding.ToBi.entity.CustomerInformationEntity;
import com.AiFunding.ToBi.mapper.CustomerInformationRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HomeService {

    private final static Double PURCHASE_FEE = 0.015;
    private final static Double PUBLIC_TAX = 0.25;
    private final static Integer INITIAL_PRICE = 10000000;

    private final CustomerInformationRepository customerInformationRepository;

    public HomeService(CustomerInformationRepository customerInformationRepository) {
        this.customerInformationRepository = customerInformationRepository;
    }


    public UserResponseDto findUserInfo(final Long id, final String loginType){
        Optional<CustomerInformationEntity> customer = customerInformationRepository.findByIdAndLoginType(id,loginType);
        customer.orElseThrow(NullPointerException::new);
        return new UserResponseDto(customer.get().getNickname(),getAccountData(customer.get().getAccounts()));

    }

    public List<AccountListResponseDto> getAccountData(List<AccountEntity> accounts){
        List<AccountListResponseDto> accountData = new ArrayList<>();

        for(AccountEntity account : accounts){
            Long stockBalance = account.getBalance(); // 현재 보유하고 있는 주식의 총 금액

            for(AccountStockDetailEntity stock : account.getAccountStocks()){ // 현재 보유하고 있는 주식의 총 금액을 더해주는 과정
                stockBalance += (long) stock.getStockAmount() * stock.getStock().getNowPrice();
            }
            Double todayIncome = (double)(account.getTodayTotalBalance()-account.getYesterdayTotalBalance()) / account.getYesterdayTotalBalance() * 100;
            Double totalIncome = (double)(stockBalance - INITIAL_PRICE) /INITIAL_PRICE * 100;

            accountData.add(new AccountListResponseDto(account.getNickname(), stockBalance,account.getCreateAt()
            ,Math.floor(todayIncome*100) / 100.0
            , (account.getTodayTotalBalance()- account.getYesterdayTotalBalance())
                    , Math.floor(totalIncome*100)/100.0, (stockBalance-INITIAL_PRICE),getStockData(account)));
        }

        Collections.sort(accountData, Comparator.comparing(AccountListResponseDto::getCreateAt)); // createAt 별로 정렬
        return accountData;
    }

    // Stock에 대한 하단 정보를 얻기 위한 메서드
    public List<StockListResponseDto> getStockData(AccountEntity account){
        List<StockListResponseDto> stockList = new ArrayList<>();

        // 계좌 총 자산
        Long balance = 0L;

        // 계좌가 가지고 있는 주식의 금액 보유량 구하기 -> 계좌에서 차지하는 비율을 구하기 위함
        for(AccountStockDetailEntity stocks : account.getAccountStocks()){
            balance += (long) stocks.getStock().getNowPrice() * stocks.getStockAmount();
        }


        for(AccountStockDetailEntity stocks : account.getAccountStocks()){
            //TODO: 해당 주식 id가 없을 경우 발생하는 오류 Exception 추가할 것

            Double accountPercent = (double)stocks.getStockAmount() * (double)stocks.getStock().getNowPrice() / (double)balance * 100.0;
            Double profit = (double)(stocks.getStock().getNowPrice() - stocks.getAveragePrice()) / stocks.getAveragePrice() * 100;

            stockList.add(new StockListResponseDto(
                    stocks.getStock().getItemName(),stocks.getStock().getNowPrice(),
                    Math.floor(profit*100)/100.0,(double)Math.floor(accountPercent*100)/100.0
            ));
        }
        Collections.sort(stockList,(StockListResponseDto s1, StockListResponseDto s2)->{
            if (s1.getPercent_by_account() < s2.getPercent_by_account()) return 1;
            else if (s1.getPercent_by_account() > s2.getPercent_by_account()) return -1;
            return 0;
        }); // 점유율에 대해서 내림차순으로 정렬
        return stockList;
    }

}
