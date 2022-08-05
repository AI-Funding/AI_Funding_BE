package com.AiFunding.ToBi.controller.community;

import com.AiFunding.ToBi.dto.community.chat.CommunityChatResponseDto;
import com.AiFunding.ToBi.service.community.CommunityChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/community-chat")
public class CommunityChatController {
    private final CommunityChatService communityChatService;

    public CommunityChatController(CommunityChatService communityChatService) {
        this.communityChatService = communityChatService;
    }

    @GetMapping
    public ResponseEntity<CommunityChatResponseDto> responseCommunityChat() {
        return ResponseEntity.ok().body(communityChatService.findCommunityChatBoard());
    }
}
