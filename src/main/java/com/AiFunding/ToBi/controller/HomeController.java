package com.AiFunding.ToBi.controller;

import com.AiFunding.ToBi.dto.Home.AccountListResponseDto;
import com.AiFunding.ToBi.dto.Home.UserRequestDto;
import com.AiFunding.ToBi.dto.Home.UserResponseDto;
import com.AiFunding.ToBi.dto.Home.StockListResponseDto;
import com.AiFunding.ToBi.service.home.HomeService;
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


    @PostMapping("/home")
    public ResponseEntity<UserResponseDto> join(@RequestBody UserRequestDto userRequestDto){

        return ResponseEntity.ok().body(homeService.findUserInfo(userRequestDto.getId(), userRequestDto.getLoginType()));
    }

}
