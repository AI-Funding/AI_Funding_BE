package com.AiFunding.ToBi.service.profit;

import com.AiFunding.ToBi.dto.profit.AccumulatedProfitResponseDto;
import com.AiFunding.ToBi.dto.profit.ProfitAccountInfoResponseDto;
import com.AiFunding.ToBi.dto.profit.ProfitAccountListDto;
import com.AiFunding.ToBi.entity.AccountEntity;
import com.AiFunding.ToBi.entity.AccountStockDetailEntity;
import com.AiFunding.ToBi.entity.CustomerInformationEntity;
import com.AiFunding.ToBi.entity.TradingDetailEntity;
import com.AiFunding.ToBi.mapper.AccountStockDetailRepository;
import com.AiFunding.ToBi.mapper.CustomerInformationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfitCompareService {
    private final CustomerInformationRepository customerInformationRepository;//사용자 정보 Repository
    private final AccountStockDetailRepository accountStockDetailRepository;//계좌의 주식 상세 정보 Repository

    public ProfitCompareService(CustomerInformationRepository customerInformationRepository, AccountStockDetailRepository accountStockDetailRepository) {
        this.customerInformationRepository = customerInformationRepository;
        this.accountStockDetailRepository = accountStockDetailRepository;
    }

    /**
     * @param id
     * @param loginType
     * @return 해당 사용자의 수익 정보를 찾아서 반환함
     */
    public ProfitAccountListDto findUserProfit(final Long id, final String loginType) {
        Optional<CustomerInformationEntity> customerInfo = customerInformationRepository.findByIdAndLoginType(id, loginType);
        customerInfo.orElseThrow(NullPointerException::new);

        return new ProfitAccountListDto(getProfitAccountInfoResponseDtoList(customerInfo.get()));
    }

    /**
     * @param customerInfo
     * @return 사용자가 가지고 있는 모든 계좌의 수익정보를 list로 반환
     */
    public List<ProfitAccountInfoResponseDto> getProfitAccountInfoResponseDtoList(CustomerInformationEntity customerInfo) {
        List<ProfitAccountInfoResponseDto> profitAccountInfoResponseDtoList = new ArrayList<>();
        for (AccountEntity accountEntity : customerInfo.getAccounts()) {
            //ai번호
            Integer aiType =  accountEntity.getAiType();
            //계좌이름
            String nickname = accountEntity.getNickname();
            //계좌번호
            String accountNumber = accountEntity.getAccountNumber();
            //일별 누적수익률 리스트
            List<AccumulatedProfitResponseDto> profits = getAccumulatedProfitResponseDtoList(accountEntity);

            profitAccountInfoResponseDtoList.add(new ProfitAccountInfoResponseDto(aiType, nickname, accountNumber, profits));
        }
        return profitAccountInfoResponseDtoList;

    }

    /**
     * @return 일별 누적수익률 리스트를 반환
     */
    public List<AccumulatedProfitResponseDto> getAccumulatedProfitResponseDtoList(AccountEntity accountEntity) {
        List<AccumulatedProfitResponseDto> accumulatedProfitResponseDtoList = new ArrayList<>();

        //계좌의 종목거래내역 List로부터 첫 거래일을 구함
        List<TradingDetailEntity> tradingDetailEntityList = accountEntity.getTradingEntities();
        LocalDateTime currDate = tradingDetailEntityList.get(0).getCreateAt();


        //해당 거래날짜 기준으로 다음 거래가 생기기 전 까지의 누적 수익률을 구함
        LocalDateTime currStartTime = LocalDateTime.of(currDate.getYear(),currDate.getMonth(),currDate.getDayOfMonth(),0,0,0);
        LocalDateTime currEndTime = LocalDateTime.of(currDate.getYear(),currDate.getMonth(),currDate.getDayOfMonth(),23,59,59);
        while (currStartTime.isBefore(LocalDateTime.now())) {
            List<AccountStockDetailEntity> accountStockDetailEntityList = accountStockDetailRepository.findByCreateAtBetweenAndAccount(currStartTime, currEndTime, accountEntity);

            if(accountStockDetailEntityList.isEmpty()) break;//

            int stockSum = 0;//누적수익금액
            //해당일 기준 계좌의 모든 주식
            for (AccountStockDetailEntity accountStockDetailEntity : accountStockDetailEntityList) {
                //현재가 * 수량
                stockSum += accountStockDetailEntity.getStock().getNowPrice() * accountStockDetailEntity.getStockAmount();
            }
            //현재주식가 + 잔고 / 1000
            Long money = 10000000L;
            double currProfit = (double)(stockSum + accountEntity.getBalance()) / money * 100;//현재의 누적수익률
            accumulatedProfitResponseDtoList.add(new AccumulatedProfitResponseDto(currStartTime, currProfit));

            //하루증가
            currStartTime = currStartTime.plusDays(1);
            currEndTime = currEndTime.plusDays(1);
        }
        return accumulatedProfitResponseDtoList;
    }
}