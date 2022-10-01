package com.AiFunding.ToBi.service.setting;

import com.AiFunding.ToBi.dto.setting.ChangeAccountNameDto;
import com.AiFunding.ToBi.dto.setting.ChangeUserNameDto;
import com.AiFunding.ToBi.entity.AccountEntity;
import com.AiFunding.ToBi.entity.CustomerInformationEntity;
import com.AiFunding.ToBi.mapper.AccountRepository;
import com.AiFunding.ToBi.mapper.CustomerInformationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        if(!isChanged){
            throw new Exception("해당하는 유저가 없습니다.");
        }
    }

    public void changeUserName(ChangeUserNameDto userName) throws Exception {
        if(customerInformationRepository.existsByNickname(userName.getChangedName())){
            throw new Exception("중복되는 유저가 존재합니다.");
        }
        CustomerInformationEntity findUser = customerInformationRepository.
                findByIdAndLoginType(userName.getCustomerInfoId(), userName.getLoginType())
                .orElseThrow(() -> new Exception("해당하는 유저가 없습니다"));

        CustomerInformationEntity newUser = CustomerInformationEntity
                .builder()
                .id(findUser.getId())
                .userId(findUser.getUserId())
                .refreshToken(findUser.getRefreshToken())
                .accounts(findUser.getAccounts())
                .nickname(userName.getChangedName())
                .email(findUser.getEmail())
                .loginType(findUser.getLoginType())
                .comments(findUser.getComments())
                .password(findUser.getPassword())
                .posts(findUser.getPosts())
                .subscribeInfo(findUser.getSubscribeInfo())
                .build();

        customerInformationRepository.save(newUser);
    }


}
