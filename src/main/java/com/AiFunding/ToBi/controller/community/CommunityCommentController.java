package com.AiFunding.ToBi.controller.community;

import com.AiFunding.ToBi.dto.IsSuccessDto;
import com.AiFunding.ToBi.dto.community.CommunityCommentDto;
import com.AiFunding.ToBi.service.community.CommunityCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/community-comment")
public class CommunityCommentController {
    private final CommunityCommentService communityCommentService;

    public CommunityCommentController(CommunityCommentService communityCommentService) {
        this.communityCommentService = communityCommentService;
    }

    @PostMapping
    public ResponseEntity<IsSuccessDto> ResponseStoreCommentResult(@RequestBody CommunityCommentDto communityCommentDto) {
        return ResponseEntity.ok().body(communityCommentService.storeComment(communityCommentDto));
    }

}
