package com.AiFunding.ToBi.dto.community;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommunityCommentDto {
    //댓글 작성자 정보 Id
    @JsonProperty("customer_info_id")
    private Long customerId;
    //게시판 Id
    @JsonProperty("board_id")
    private Long boardId;
    //부모게시글 사용자 Id
    @JsonProperty("parent_id")
    private Long parentId;
    //게시글 id
    @JsonProperty("post_id")
    private Long postId;
    //댓글 내용
    private String content;

    public CommunityCommentDto(final Long customerId,
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
