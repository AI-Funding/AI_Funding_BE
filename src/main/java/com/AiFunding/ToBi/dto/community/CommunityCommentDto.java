package com.AiFunding.ToBi.dto.community;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommunityCommentDto {
    //댓글 작성자
    private String commentWriter;
    //댓글 내용
    private String commentContent;
    //댓글 작성 날짜
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime commentDate;
    //댓글 좋아요 수
    private Integer commentHeartNum;

    public CommunityCommentDto(final String commentWriter,
                               final String commentContent,
                               final LocalDateTime commentDate,
                               final Integer commentHeartNum) {
        this.commentWriter = commentWriter;
        this.commentContent = commentContent;
        this.commentDate = commentDate;
        this.commentHeartNum = commentHeartNum;
    }
}
