package com.AiFunding.ToBi.controller;

import com.AiFunding.ToBi.dto.Home.UserRequestDto;
import com.AiFunding.ToBi.dto.ai.History.AccountTradeHistoryResponseDto;
import com.AiFunding.ToBi.dto.ai.History.TradeHistoryListResponseDto;
import com.AiFunding.ToBi.service.ai.history.HistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai-page")
public class HistoryController {
    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @PostMapping("/history")
    public ResponseEntity<AccountTradeHistoryResponseDto> join(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok().body(historyService.findUserHistory(userRequestDto.getId(), userRequestDto.getLoginType()));
    }
}
