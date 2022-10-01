package com.AiFunding.ToBi.dto.community;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommunityWritePostDto {
    @JsonProperty("customer_info_id")
    private Long customerId;

    @JsonProperty("board_id")
    private Long boardId;

    private String title;

    private String content;

    public CommunityWritePostDto(final Long customerId,
                                 final Long boardId,
                                 final String title,
                                 final String content) {
        this.customerId = customerId;
        this.boardId = boardId;
        this.title = title;
        this.content = content;
    }
}
