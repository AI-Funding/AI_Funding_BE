package com.AiFunding.ToBi.service.ai.page;

import com.AiFunding.ToBi.dto.ai.AiResponseDto;
import com.AiFunding.ToBi.dto.ai.History.AccountTradeHistoryResponseDto;
import com.AiFunding.ToBi.dto.ai.History.TradeHistoryListResponseDto;
import com.AiFunding.ToBi.dto.ai.History.TradeHistoryResponseDto;
import com.AiFunding.ToBi.dto.ai.page.AccountInfoResponseDto;
import com.AiFunding.ToBi.dto.ai.page.CurrStockItemsResponseDto;
import com.AiFunding.ToBi.dto.ai.page.StockDetailResponseDto;
import com.AiFunding.ToBi.dto.ai.page.StockInfoResponseDto;
import com.AiFunding.ToBi.entity.*;
import com.AiFunding.ToBi.mapper.AccountStockDetailRepository;
import com.AiFunding.ToBi.mapper.CustomerInformationRepository;
import com.AiFunding.ToBi.mapper.StockPriceByDayRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class AiService {

    private final CustomerInformationRepository customerInformationRepository;//고객정보 Repository
    private final StockPriceByDayRepository stockPriceByDayRepository;//주가변동내용 날짜별 주식가격 Repository
    private final AccountStockDetailRepository accountStockDetailRepository;//계좌주식잔고 주식의세부정보 Repository

    public static final LocalDateTime TODAY_START_TIME = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0));
    public static final LocalDateTime TODAY_END_TIME = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));

    //Repository 초기화
    public AiService(CustomerInformationRepository customerInformationRepository, StockPriceByDayRepository stockPriceByDayRepository, AccountStockDetailRepository accountStockDetailRepository) {
        this.customerInformationRepository = customerInformationRepository;
        this.stockPriceByDayRepository = stockPriceByDayRepository;
        this.accountStockDetailRepository = accountStockDetailRepository;
    }

    //id와 loginType을 가지는 Entitiy를 찾아서 DTO에 담고 반환
    public AiResponseDto findUserInfo(final Long id, final String loginType) {
        CustomerInformationEntity customerInfo = customerInformationRepository.findByIdAndLoginType(id, loginType);//customerinformation

        return new AiResponseDto(new CurrStockItemsResponseDto(getAccountDtoList(customerInfo.getAccounts())), new AccountTradeHistoryResponseDto(getAccountHistoryDtoList(customerInfo.getAccounts())));//CurrStockItemsResponseDto(getAccountDtoList(customerInfo.getAccounts()));
    }

    //계좌이름과 보유주식리스트를 계좌DTO에 담아서  반환
    public List<AccountInfoResponseDto> getAccountDtoList(List<AccountEntity> accounts) {
        List<AccountInfoResponseDto> accountDtoList = new ArrayList<>();

        for (AccountEntity account : accounts) {
            accountDtoList.add(new AccountInfoResponseDto(account.getNickname(),getStockDtoList(account)));
        }
        return accountDtoList;
    }

    //주식정보들을 Dto로 담아서 반환
    public List<StockInfoResponseDto> getStockDtoList(AccountEntity account) {
        List<StockInfoResponseDto> stockDtoList = new ArrayList<>();

        //계좌Entity에 있는 모든 주식들을 하나씩 가져온다
        for (AccountStockDetailEntity stock : account.getAccountStocks()) {
            List<StockPriceByDayEntity> stockPriceByDayEntities = stockPriceByDayRepository.findByStockAndCreateAtBetween(stock.getStock(), TODAY_START_TIME, TODAY_END_TIME);//오늘 주식가격변동내용 Entity
            Integer yesterdayStockPrice = stockPriceByDayEntities.get(stockPriceByDayEntities.size()-1).getEndPrice();//어제주식가격
            Integer todayStockPrice = stock.getStock().getNowPrice();//오늘 주식가격
            Integer stockPriceChange = todayStockPrice - yesterdayStockPrice;//어제주식가격 대비 상승가격
            double stockRateChange = ((double)(todayStockPrice - yesterdayStockPrice) / yesterdayStockPrice) * 100;

            //상승 하락 변동률 계산
            int stockChange = 0;
            if (stockRateChange > 0)
                stockChange = 1;
            else if(stockRateChange < 0)
                stockChange = -1;

            stockDtoList.add(new StockInfoResponseDto(stock.getStock().getItemName(), new Long(todayStockPrice), new Long(stockPriceChange), stockRateChange, stockChange ,getStockDetailDtoList(stock)));
        }
        return  stockDtoList;
    }

    //주식 세부정보를 Dto에 담아서 반환
    public List<StockDetailResponseDto> getStockDetailDtoList(AccountStockDetailEntity stocks) {
        List<StockDetailResponseDto> stockDetailDtoList = new ArrayList<>();

        for (StockPriceByDayEntity stockPriceByDay : stocks.getStock().getStockPriceByDayEntities()) {
            stockDetailDtoList.add(new StockDetailResponseDto(stockPriceByDay.getEndPrice(), stockPriceByDay.getCreateAt()));
        }
        return stockDetailDtoList;
    }

//거래내역===================================================================================================================================
    // 거래내역 관련 데이터 리스트 반환
    public List<TradeHistoryListResponseDto> getAccountHistoryDtoList(List<AccountEntity> accounts){
        List<TradeHistoryListResponseDto> tradeHistoryListResponseDtos = new ArrayList<>();

        for(AccountEntity account : accounts){
            tradeHistoryListResponseDtos.add(new TradeHistoryListResponseDto(account.getNickname(), getHistoryDtoList(account.getTradingEntities())));  //new TradeHistoryListResponseDto(account.getNickname(), getHistoryDtoList(account.getTradingEntities()))
        }

        return tradeHistoryListResponseDtos;
    }

    public List<TradeHistoryResponseDto> getHistoryDtoList(List<TradingDetailEntity> tradings){
        List<TradeHistoryResponseDto> historyDtoList = new ArrayList<>();

        for(TradingDetailEntity trading : tradings){
            String tradeType = trading.getTradingType();                //거래 종류
            Integer tradingAmount = trading.getTradingAmount();         //거래 수량
            Long tradingPrice = trading.getTradingPrice();              //매수/도 가
            Long totalPrice = trading.getTradingPrice();                //총 거래 금액
            Long currentPrice = totalPrice / (long)tradingAmount;       //단가, 1주당 가격

            historyDtoList.add(new TradeHistoryResponseDto(trading.getStock().getItemName(), trading.getCreateAt(),
                    totalPrice, tradeType, tradingAmount, currentPrice, tradingPrice));
        }

        Collections.sort(historyDtoList, Comparator.comparing(TradeHistoryResponseDto::getTradeDate));   //시간 순 정렬
        return historyDtoList;
    }

}



