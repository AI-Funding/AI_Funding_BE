package com.AiFunding.ToBi.controller;

import com.AiFunding.ToBi.dto.Home.UserRequestDto;
import com.AiFunding.ToBi.dto.ai.page.AccountInfoResponseDto;
import com.AiFunding.ToBi.dto.ai.page.CurrStockItemsResponseDto;
import com.AiFunding.ToBi.dto.ai.page.StockDetailResponseDto;
import com.AiFunding.ToBi.dto.ai.page.StockInfoResponseDto;
import com.AiFunding.ToBi.entity.StockEntity;
import com.AiFunding.ToBi.mapper.StockPriceByDayRepository;
import com.AiFunding.ToBi.service.ai.page.AiService;
import com.AiFunding.ToBi.service.home.HomeService;
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
    private final AiService aiService;

    public AiPageController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/stockitems")
    public ResponseEntity<CurrStockItemsResponseDto> aiPageService(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok().body(aiService.findUserCurrStockItems(userRequestDto.getId(), userRequestDto.getLoginType()));
    }
}