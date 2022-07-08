package com.AiFunding.ToBi.service.trade.signal;

import com.AiFunding.ToBi.dto.trade.signal.TradeSignalDetailDto;
import com.AiFunding.ToBi.dto.trade.signal.TradeSignalRequestDto;
import com.AiFunding.ToBi.entity.*;
import com.AiFunding.ToBi.mapper.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TradeSignalService {

    private final CustomerInformationRepository customerRepository;
    private final AccountRepository accountRepository;
    private final AccountDetailRepository accountDetailRepository;
    private final TradingDetailRepository tradingDetailRepository;
    private final AccountStockDetailRepository accountStockDetailRepository;
    private final StockRepository stockRepository;

    // Buy를 해야할 Customer 정보를 가져옵니다.
    public boolean getCustomerForBuy(TradeSignalRequestDto tradeSignal) throws Exception {

        List<CustomerInformationEntity> customers = customerRepository.findAll(); // 모든 customer 정보 가져오기

        for (CustomerInformationEntity customer : customers) { // 각각의 Customer 가져오기
            for(AccountEntity account : customer.getAccounts()){ // Customer에서 AccountEntity에 대해서 가져오기
                accountByStock(account, tradeSignal);
            }
        }
        return true;
    }

    public void accountByStock(AccountEntity account, TradeSignalRequestDto trade) throws Exception {
        Long accountBalance = account.getBalance(); // 계좌 보유 잔고

        for (TradeSignalDetailDto tradeDetail : trade.getStockInfo()) {
            // 매수 신호일 때
            if (tradeDetail.getTradeSignal() != 2 ) {

                Long accountBuyAmount = (long)(accountBalance * tradeDetail.getTradeAmount()); // 살수 있는 최대 금액
                if(tradeDetail.getTradeModel().equals("SIRL")){ // SIRL 모델에 대해서 생성
                    TradingDetailEntity tradingEntity = buyOrSellTradingDetail(account, tradeDetail, accountBuyAmount);
                    buyOrSellAccountDetail(account, tradingEntity);
                    buyOrSellAccountStockDetail(tradingEntity);
                    updateAccountBalance(account);
                }else if(tradeDetail.getTradeModel().equals("HDRL")){ // HDRL 모델에 대해서 생성
                    TradingDetailEntity tradingEntity = buyOrSellTradingDetail(account, tradeDetail, accountBuyAmount);
                    buyOrSellAccountDetail(account, tradingEntity);
                    buyOrSellAccountStockDetail(tradingEntity);
                    updateAccountBalance(account);
                }

            }
        }
    }

    public TradingDetailEntity buyOrSellTradingDetail(AccountEntity account, TradeSignalDetailDto trade, Long AccountBalance) throws Exception {
        StockEntity stock = stockRepository.findByItemName(trade.getStockName()).orElseThrow(()-> new Exception());
        Integer stockPrice = (int)(stock.getNowPrice() * 1.015);
        Integer stockAmount = (int)(AccountBalance * trade.getTradeAmount() / stockPrice);

        // TradingDetailEntity 생성
        TradingDetailEntity tradingDetail = TradingDetailEntity.builder()
                .tradingType((trade.getTradeSignal() == 0) ? "00" : "01")
                .account(account)
                .stock(stock)
                .tradingAmount(stockAmount)
                .tradingPrice((long)stockPrice * (long)stockAmount).build();

        tradingDetailRepository.save(tradingDetail); // tradingDetailRepository DB에 저장
        return tradingDetail;
    }

    public void buyOrSellAccountDetail(AccountEntity account, TradingDetailEntity trade){
        AccountDetailEntity accountDetail = AccountDetailEntity.builder()
                .account(account)
                .depositAmount(trade.getTradingPrice())
                .depositType(trade.getTradingType())
                .build();
        accountDetailRepository.save(accountDetail); // AccountDetail에 데이터 저장
    }

    public void buyOrSellAccountStockDetail(TradingDetailEntity trade) throws Exception {

        AccountStockDetailEntity findData = accountStockDetailRepository
                .findByStockAndId(trade.getStock(), trade.getAccount().getId()); // 각 주식에 맞는 데이터 값 가져오기

        StockEntity stockInfo = stockRepository.findByItemName(findData.getStock().getItemName())
                .orElseThrow(() -> new Exception()); // stock 정보 가져오기

        Long averagePrice = 0L;

        if(trade.getTradingType().equals("00")){ // 만약 거래타입이 00(입금) 일 경우
            averagePrice = ((findData.getAveragePrice() * findData.getStockAmount() + trade.getTradingPrice()) / (trade.getTradingAmount())+findData.getStockAmount());
        } else{ // 만약, 거래타입이 출금일 경우
            averagePrice = ((findData.getAveragePrice() * findData.getStockAmount() - trade.getTradingPrice()) / (trade.getTradingAmount()) - findData.getStockAmount());
            if(averagePrice < 0) averagePrice = 0L;
        }

        Integer stockAmount = findData.getStockAmount() + ((trade.getTradingType() == "00") ? trade.getTradingAmount() : -trade.getTradingAmount());

        // AccountStockDetail에 새로운 DB 데이터 생성
        AccountStockDetailEntity newData = AccountStockDetailEntity
                .builder()
                .id(findData.getId())
                .account(findData.getAccount())
                .stock(findData.getStock())
                .stockAmount(stockAmount)
                .averagePrice( (int)(((trade.getTradingPrice() * trade.getTradingAmount()) +
                        (findData.getStockAmount() + findData.getAveragePrice())) / (trade.getTradingAmount() + findData.getStockAmount())))
                .income((int) ((averagePrice * stockAmount ) - (stockInfo.getNowPrice() * stockAmount)))
                .build();

        accountStockDetailRepository.save(newData); // AccountStockDetail 저장

    }

    public void updateAccountBalance(AccountEntity account){

        LocalDateTime startTime = LocalDateTime.of(LocalDate.now() , LocalTime.of(0,0,0));
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));

        List<TradingDetailEntity> tradeData = tradingDetailRepository.findByCreateAtBetweenAndAccount(startTime, endTime, account);

        Long balance = account.getBalance();

        Long todayBalance = account.getTodayTotalBalance() - balance;

        for(TradingDetailEntity trade : tradeData){
            if (trade.getTradingType().equals("00")){
                balance -= trade.getTradingAmount();
            }else{
                balance += trade.getTradingAmount();
            }
        }

        todayBalance += balance;

        AccountEntity updateAccount = AccountEntity.builder()
                .accountDetails(account.getAccountDetails())
                .accountNumber(account.getAccountNumber())
                .accountStocks(account.getAccountStocks())
                .aiType(account.getAiType())
                .userVisiable(account.getUserVisiable())
                .nickname(account.getNickname())
                .id(account.getId())
                .yesterdayTotalBalance(account.getYesterdayTotalBalance())
                .tradingEntities(account.getTradingEntities())
                .customer(account.getCustomer())
                .balance(balance)
                .todayTotalBalance(todayBalance)
                .build();
        accountRepository.save(updateAccount);

    }
}
