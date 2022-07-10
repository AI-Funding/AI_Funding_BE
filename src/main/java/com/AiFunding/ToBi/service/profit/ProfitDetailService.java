package com.AiFunding.ToBi.service.profit;

import com.AiFunding.ToBi.dto.profit.AccountProfitResponseDto;
import com.AiFunding.ToBi.dto.profit.ProfitCheckResponseDto;
import com.AiFunding.ToBi.dto.profit.ProfitDetailResponseDto;
import com.AiFunding.ToBi.entity.AccountEntity;
import com.AiFunding.ToBi.entity.AccountStockDetailEntity;
import com.AiFunding.ToBi.entity.CustomerInformationEntity;
import com.AiFunding.ToBi.mapper.AccountStockDetailRepository;
import com.AiFunding.ToBi.mapper.CustomerInformationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfitDetailService {
    private final CustomerInformationRepository customerInformationRepository;
    private final AccountStockDetailRepository accountStockDetailRepository;

    public ProfitDetailService(CustomerInformationRepository customerInformationRepository, AccountStockDetailRepository accountStockDetailRepository) {
        this.customerInformationRepository = customerInformationRepository;
        this.accountStockDetailRepository = accountStockDetailRepository;
    }
    public ProfitCheckResponseDto findUserDetail(final Long id, final String loginType) {
        //id와 loginType을 가지는 사용자 정보 Entity
        Optional<CustomerInformationEntity> customerInfo = customerInformationRepository.findByIdAndLoginType(id, loginType);//customerinformation
        customerInfo.orElseThrow();
        return new ProfitCheckResponseDto(getAccountProfitDtoList(customerInfo.get().getAccounts()),customerInfo.get().getAccounts().size());
    }
    //계좌 수익 리스트 반환

    /**
     *
     * @param accountEntities
     * @return 계좌정보 DTO List
     */
    public List<AccountProfitResponseDto> getAccountProfitDtoList(List<AccountEntity> accountEntities) {
        List<AccountProfitResponseDto> accountProfitDtoList = new ArrayList<>();
        Integer sum = 0;//모든 주식의 수익률 합

        for(AccountEntity accountEntity : accountEntities){
            //투자 Ai Type
            Integer aiType = accountEntity.getAiType();
            //계좌 이름
            String accountName = accountEntity.getNickname();
            //총 평가 금액((평단가*수량)모든주식총합)
            Long todayTotalBalance = accountEntity.getTodayTotalBalance();
            //총 손익금 (원₩) -> 계좌에 있는 모든 주식의 수익금 합
            var accountStocks = accountEntity.getAccountStocks();//계좌에 있는 모든 주식값을 가져옴

            for (AccountStockDetailEntity accountStockDetailEntity:accountStocks) {//모든 주식의 수익률를 더함
                sum += accountStockDetailEntity.getIncome();
            }
            Long totalProfitWon = sum.longValue();
            //총 손익금 (퍼센트) (총수익금/총 평가 금액)*100
            Double totalProfitPersent = (double)totalProfitWon/todayTotalBalance*100;
            Long todayProfitWon = todayTotalBalance-accountEntity.getYesterdayTotalBalance();
            //하루 수익률 (퍼센트) -> (하루손익금/어제 평단가*수량) * 100
            Double todayProfitPersent = (double)todayProfitWon/accountEntity.getYesterdayTotalBalance()*100;
            //수익률 세부정보 리스트
            List<ProfitDetailResponseDto> profitDetails = getProfitDetailDtoList(accountEntity);

            accountProfitDtoList.add(new AccountProfitResponseDto(
                    aiType,
                    accountName,
                    todayTotalBalance,
                    totalProfitPersent,
                    totalProfitWon,
                    todayProfitWon,
                    todayProfitPersent,
                    profitDetails
            ));
        }
        return accountProfitDtoList;
    }
    /**
     *
     * @param accountEntity
     * @return 수익상세 리스트 반환환     */
    public List<ProfitDetailResponseDto> getProfitDetailDtoList(AccountEntity accountEntity){
        //수익상세 리스트
        List<ProfitDetailResponseDto> profitDetailDtoList = new ArrayList<>();
        Integer profitSum = 0;
        Integer averageSum = 0;

        //계좌의 모든 주식의 7일동안의 수익금 합을 구함 / 날짜기준으로 주식상세 list를 반환 받음
        LocalDateTime TODAY_START_TIME = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0));
        LocalDateTime TODAY_END_TIME = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));
        for (int i = 0; i < 7; i++) {
            //해당 날짜의 모든 주식의 수익금합,평단가합을 구함
            List<AccountStockDetailEntity> accountStockDetailEntityList = accountStockDetailRepository.findByCreateAtBetweenAndAccount(TODAY_START_TIME, TODAY_END_TIME, accountEntity);
            for (var accountStockDetail : accountStockDetailEntityList) {
                profitSum += accountStockDetail.getIncome();
                averageSum += accountStockDetail.getAveragePrice() * accountStockDetail.getStockAmount();//평단가*수량
            }
            //해당일 손익금(원₩)
            Long creatAtProfitWon = profitSum.longValue();
            //해당일 손익금(퍼센트)->해당일손익금/(평단가*수량)*100
            Double creatAtProfitPersent = (double)creatAtProfitWon/averageSum*100;
            //해당 날짜
            LocalDateTime creatAt = TODAY_START_TIME;

            profitDetailDtoList.add(new ProfitDetailResponseDto(creatAtProfitPersent, creatAtProfitWon, creatAt));
            //수익금합 평단가*수량 초기화
            profitSum = 0;
            averageSum = 0;
            //하루 전으로 설정
            TODAY_START_TIME = TODAY_START_TIME.minusDays(1);
            TODAY_END_TIME = TODAY_END_TIME.minusDays(1);
        }

        return profitDetailDtoList;
    }
}