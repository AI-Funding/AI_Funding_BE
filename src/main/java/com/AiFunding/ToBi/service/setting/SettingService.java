package com.AiFunding.ToBi.service.setting;

import com.AiFunding.ToBi.dto.setting.ChangeAccountNameDto;
import com.AiFunding.ToBi.entity.AccountEntity;
import com.AiFunding.ToBi.entity.CustomerInformationEntity;
import com.AiFunding.ToBi.mapper.AccountRepository;
import com.AiFunding.ToBi.mapper.CustomerInformationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SettingService {

    private final CustomerInformationRepository customerInformationRepository;
    private final AccountRepository accountRepository;

    public CustomerInformationEntity findByAccountName(Long userId) throws Exception {

        return customerInformationRepository.findById(userId).orElseThrow(() -> new Exception("해당하는 유저가 없습니다"));

    }

    public void changeAccountName(ChangeAccountNameDto accountNameDto) throws Exception {
        List<AccountEntity> findAccounts = findByAccountName(accountNameDto.getCustomerInfoId()).getAccounts();
        boolean isChanged = false;
        for(AccountEntity account : findAccounts)
        {
            if(account.getNickname().equals(accountNameDto.getCurrentName())){
                AccountEntity changeAccount = AccountEntity.builder()
                        .id(account.getId())
                        .accountDetails(account.getAccountDetails())
                        .accountNumber(account.getAccountNumber())
                        .accountStocks(account.getAccountStocks())
                        .balance(account.getBalance())
                        .aiType(account.getAiType())
                        .customer(account.getCustomer())
                        .nickname(accountNameDto.getChangedName())
                        .todayTotalBalance(account.getTodayTotalBalance())
                        .tradingEntities(account.getTradingEntities())
                        .userVisiable(account.getUserVisiable())
                        .yesterdayTotalBalance(account.getYesterdayTotalBalance())
                        .build();
                accountRepository.save(changeAccount);
                isChanged = true;
            }
        }

        if(isChanged == false){
            throw new Exception("해당하는 유저가 없습니다.");
        }
    }
}
