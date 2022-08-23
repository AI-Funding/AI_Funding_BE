package com.AiFunding.ToBi.dto.community;

import com.AiFunding.ToBi.dto.community.chat.CommunityChatCommentDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class CommunityPostDto {
    //게시글 id
    private Long id;
    //글 제목
    private String title;
    //작성날짜
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime date;
    //하트 수
    private Integer heartNum;
    //댓글 개수
    private Integer commentNum;
    //내용
    private String content;
    //작성자
    private String writer;
    //댓글
    private List<CommunityChatCommentDto> comments;

    public CommunityPostDto(final Long id,
                            final String title,
                            final LocalDateTime date,
                            final Integer heartNum,
                            final Integer commentNum,
                            final String content,
                            final String writer,
                            final List<CommunityChatCommentDto> comments) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.heartNum = heartNum;
        this.commentNum = commentNum;
        this.content = content;
        this.writer = writer;
        this.comments = comments;
    }
}
