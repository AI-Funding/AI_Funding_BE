package com.AiFunding.ToBi.controller;

import com.AiFunding.ToBi.dto.trade.signal.TradeSignalRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TradeSignalController {

    @PostMapping("/signal")
    public ResponseEntity<TradeSignalRequestDto> getSignal(@RequestBody TradeSignalRequestDto trade){

        return ResponseEntity.ok().body(trade);
    }
}
