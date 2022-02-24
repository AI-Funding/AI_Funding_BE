package com.AiFunding.ToBi.controller;

import com.AiFunding.ToBi.dto.Home.UserRequestDto;
import com.AiFunding.ToBi.dto.ai.page.AccountInfoResponseDto;
import com.AiFunding.ToBi.dto.ai.page.CurrStockItemsResponseDto;
import com.AiFunding.ToBi.dto.ai.page.StockDetailResponseDto;
import com.AiFunding.ToBi.dto.ai.page.StockInfoResponseDto;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/ai-page")
public class AiPageController {

    @PostMapping("/stockitems")
    public ResponseEntity<CurrStockItemsResponseDto> aiPageMapping(){
        List<AccountInfoResponseDto> accounts = new ArrayList<>();


        accounts.add(new AccountInfoResponseDto("서진계좌", Arrays.asList(
                new StockInfoResponseDto("삼성전자", 10000L, 2000L, 2.9, 1,
                        Arrays.asList(new StockDetailResponseDto(12000, LocalDateTime.now()))),
                new StockInfoResponseDto("SK하이닉스",25000L, 500L, 10.2,1,
                        Arrays.asList(new StockDetailResponseDto(10000, LocalDateTime.now())))
                )));
        accounts.add(new AccountInfoResponseDto("해찬계좌", Arrays.asList(
                new StockInfoResponseDto("LG", 10000L, 2000L, 2.9, 1,
                        Arrays.asList(new StockDetailResponseDto(12000, LocalDateTime.now()))),
                new StockInfoResponseDto("준환전자",25000L, 500L, 10.2,1,
                        Arrays.asList(new StockDetailResponseDto(10000, LocalDateTime.now())))
        )));

        CurrStockItemsResponseDto currStockItemsResponseDto = new CurrStockItemsResponseDto(accounts);


        return ResponseEntity.ok().body(currStockItemsResponseDto);
    }

}
