package com.AiFunding.ToBi.controller.community;

import com.AiFunding.ToBi.dto.Home.UserRequestDto;
import com.AiFunding.ToBi.dto.community.chat.CommunityChatResponseDto;
import com.AiFunding.ToBi.service.community.CommunityChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/community-chat")
public class CommunityChatController {
    private final CommunityChatService communityChatService;

    public CommunityChatController(CommunityChatService communityChatService) {
        this.communityChatService = communityChatService;
    }

    @PostMapping
    public ResponseEntity<CommunityChatResponseDto> responseCommunityChat(@RequestBody UserRequestDto userRequestDto) {

        return ResponseEntity.ok().body(communityChatService.findCommunityChatBoard(userRequestDto.getId(), userRequestDto.getLoginType()));
    }
}
