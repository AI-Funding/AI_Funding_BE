package com.AiFunding.ToBi.dto.community;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommunityComment {
    @JsonProperty("customer_info_id")
    private Long customerId;
    @JsonProperty("board_id")
    private Long boardId;

    @JsonProperty("parent_id")
    private Long parentId;

    @JsonProperty("post_id")
    private Long postId;
    
    private String content;

    public CommunityComment(final Long customerId,
                            final Long boardId,
                            final Long parentId,
                            final Long postId,
                            final String content) {
        this.customerId = customerId;
        this.boardId = boardId;
        this.parentId = parentId;
        this.postId = postId;
        this.content = content;
    }
}
