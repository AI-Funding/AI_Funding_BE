package com.AiFunding.ToBi.controller;

import com.AiFunding.ToBi.dto.Home.UserRequestDto;
import com.AiFunding.ToBi.dto.profit.ProfitCheckResponseDto;
import com.AiFunding.ToBi.service.profit.ProfitDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profit-check")
public class ProfitDetailController {
    private ProfitDetailService profitDetailService;

    public ProfitDetailController(ProfitDetailService profitDetailService) {
        this.profitDetailService = profitDetailService;
    }
    @PostMapping
    public ResponseEntity<ProfitCheckResponseDto> profitDetail(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok().body(profitDetailService.findUserDetail(userRequestDto.getId(), userRequestDto.getLoginType()));
    }
}