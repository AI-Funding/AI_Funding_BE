package com.AiFunding.ToBi.controller.community;

import com.AiFunding.ToBi.dto.IsSuccessDto;
import com.AiFunding.ToBi.dto.community.CommunityWritePostDto;
import com.AiFunding.ToBi.service.community.CommunityWritePostService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/community-writing")
public class CommunityWritePostController {
    private final CommunityWritePostService communityWritePostService;

    public CommunityWritePostController(CommunityWritePostService communityWritePostService) {
        this.communityWritePostService = communityWritePostService;
    }

    @PostMapping
    public ResponseEntity<IsSuccessDto> ResponseStorePostResult(@RequestBody CommunityWritePostDto communityWritePostDto) {
        return ResponseEntity.ok().body(communityWritePostService.storeWritePost(
                communityWritePostDto.getCustomerId(),
                communityWritePostDto.getBoardId(),
                communityWritePostDto.getTitle(),
                communityWritePostDto.getContent())
        );
    }
}
