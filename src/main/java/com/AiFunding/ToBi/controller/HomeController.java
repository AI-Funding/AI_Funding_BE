package com.AiFunding.ToBi.controller;

import com.AiFunding.ToBi.dto.Home.AccountListResponseDto;
import com.AiFunding.ToBi.dto.Home.UserRequestDto;
import com.AiFunding.ToBi.dto.Home.UserResponseDto;
import com.AiFunding.ToBi.dto.Home.StockListResponseDto;
import com.AiFunding.ToBi.service.HomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class HomeController {

    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/hello")
    public UserResponseDto test(){

        StockListResponseDto stock1 = new StockListResponseDto("삼성전자", 89000, 5.8, 30.0);
        StockListResponseDto stock2 = new StockListResponseDto("카카오", 12100, 12.3,40.0);
        StockListResponseDto stock3 = new StockListResponseDto("대한항공", 49000, 7.2,30.0);
        AccountListResponseDto account1 = new AccountListResponseDto("퀀트계좌",
                1000000L, LocalDateTime.now(),12.3,89000,20.3,145000
        ,Arrays.asList(stock1,stock2,stock3));
        AccountListResponseDto account2 = new AccountListResponseDto("다른 알고리즘 계좌",
                1200000L, LocalDateTime.now(),16.8,90200,24.3,234000,Arrays.asList(stock1,stock2,stock3));
        AccountListResponseDto account3 = new AccountListResponseDto("다다른 알고리즘 계좌",
                1000000L, LocalDateTime.now(),5.7,32000,8.9,75000,Arrays.asList(stock1,stock2,stock3));

        UserResponseDto dto = new UserResponseDto(
                "유해찬", Arrays.asList(account1,account2,account3)
        );
        return dto;
    }

    @PostMapping("/home")
    public ResponseEntity<UserResponseDto> join(@RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.ok().body(homeService.findUserInfo(userRequestDto.getUserSequence(), userRequestDto.getLoginType()));
    }

}
