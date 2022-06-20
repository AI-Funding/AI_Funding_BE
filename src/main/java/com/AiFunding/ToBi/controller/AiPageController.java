package com.AiFunding.ToBi.controller;

import com.AiFunding.ToBi.dto.Home.UserRequestDto;
import com.AiFunding.ToBi.dto.ai.AiResponseDto;
import com.AiFunding.ToBi.service.ai.page.AiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai-page")
public class AiPageController {
    private final AiService aiService;

    public AiPageController(AiService aiService) {
            this.aiService = aiService;
        }

        @PostMapping
        public ResponseEntity<AiResponseDto> aiPageService(@RequestBody UserRequestDto userRequestDto) {
            return ResponseEntity.ok().body(aiService.findUserInfo(userRequestDto.getId(), userRequestDto.getLoginType()));
        }
    }