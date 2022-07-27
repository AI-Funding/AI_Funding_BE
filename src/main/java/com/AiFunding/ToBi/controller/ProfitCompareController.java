package com.AiFunding.ToBi.controller;

import com.AiFunding.ToBi.dto.Home.UserRequestDto;
import com.AiFunding.ToBi.dto.profit.ProfitAccountListDto;
import com.AiFunding.ToBi.service.profit.ProfitCompareService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profit-compare")
public class ProfitCompareController {
    private final ProfitCompareService profitCompareService;

    public ProfitCompareController(ProfitCompareService profitCompareService) {
        this.profitCompareService = profitCompareService;
    }

    @PostMapping
    public ResponseEntity<ProfitAccountListDto> profitAccountInfo(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok().body(profitCompareService.findUserProfit(userRequestDto.getId(), userRequestDto.getLoginType()));
    }
}