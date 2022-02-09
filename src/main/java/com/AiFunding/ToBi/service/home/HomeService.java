package com.AiFunding.ToBi.service.home;

import com.AiFunding.ToBi.dto.Home.AccountListResponseDto;
import com.AiFunding.ToBi.dto.Home.StockListResponseDto;
import com.AiFunding.ToBi.dto.Home.UserResponseDto;
import com.AiFunding.ToBi.entity.AccountEntity;
import com.AiFunding.ToBi.entity.AccountStockDetailEntity;
import com.AiFunding.ToBi.entity.CustomerInformationEntity;
import com.AiFunding.ToBi.entity.StockEntity;
import com.AiFunding.ToBi.mapper.CustomerInformationRepository;
import com.AiFunding.ToBi.mapper.StockRepository;
import com.AiFunding.ToBi.service.comparator.AccountComparator;
import com.AiFunding.ToBi.service.comparator.StockComparator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        CustomerInformationEntity customer = customerInformationRepository.findByIdAndLoginType(id,loginType);

        return new UserResponseDto(customer.getNickname(),getAccountData(customer.getAccounts()));

    }

    public List<AccountListResponseDto> getAccountData(List<AccountEntity> accounts){
        List<AccountListResponseDto> accountData = new ArrayList<>();

        for(AccountEntity account : accounts){
            Long stockBalance = account.getBalance(); // 현재 보유하고 있는 주식의 총 금액

            for(AccountStockDetailEntity stock : account.getAccountStocks()){ // 현재 보유하고 있는 주식의 총 금액을 더해주는 과정
                stockBalance += (long) stock.getStockAmount() * stock.getAveragePrice();
            }
            Double todayIncome = (double)account.getTodayTotalBalance() / (double)account.getYesterdayTotalBalance() - 1.0;
            Double totalIncome = (double) stockBalance / (double) INITIAL_PRICE - 1.0;

            accountData.add(new AccountListResponseDto(account.getNickname(), stockBalance,account.getCreateAt()
            ,Math.floor(todayIncome*100) / 100.0
            , (account.getTodayTotalBalance()- account.getYesterdayTotalBalance())
                    , Math.floor(totalIncome*100)/100.0, (stockBalance-INITIAL_PRICE),getStockData(account)));
        }

        Collections.sort(accountData, new AccountComparator()); // createAt 별로 정렬
        return accountData;
    }

    // Stock에 대한 하단 정보를 얻기 위한 메서드
    public List<StockListResponseDto> getStockData(AccountEntity account){
        List<StockListResponseDto> stockList = new ArrayList<>();

        // 계좌 총 자산
        Long balance = 0L;

        // 계좌가 가지고 있는 주식의 금액 보유량 구하기 -> 계좌에서 차지하는 비율을 구하기 위함
        for(AccountStockDetailEntity stocks : account.getAccountStocks()){
            balance += (long) stocks.getAveragePrice() * stocks.getStockAmount();
        }


        for(AccountStockDetailEntity stocks : account.getAccountStocks()){
            //TODO: 해당 주식 id가 없을 경우 발생하는 오류 Exception 추가할 것

            Double profitAndLoss = (stocks.getAveragePrice()*stocks.getStockAmount()*(1 +PURCHASE_FEE + PUBLIC_TAX)) / stocks.getStockAmount(); // 손익단가
            Double profit = (profitAndLoss - stocks.getStock().getNowPrice()) / profitAndLoss * 100; // 수익률
            Double accountPercent = (double)stocks.getStockAmount() * (double)stocks.getAveragePrice() / (double)balance * 100.0;

            stockList.add(new StockListResponseDto(
                    stocks.getStock().getItemName(),stocks.getStock().getNowPrice(),
                    profit,(double)Math.floor(accountPercent*100)/100.0
            ));
        }
        Collections.sort(stockList,new StockComparator()); // 점유율에 대해서 내림차순으로 정렬
        return stockList;
    }
}
