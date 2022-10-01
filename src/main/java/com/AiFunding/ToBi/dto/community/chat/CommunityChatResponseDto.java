package com.AiFunding.ToBi.dto.community.chat;

import com.AiFunding.ToBi.dto.community.CommunityPostDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class CommunityChatResponseDto {
    //사용자 확인
    private Boolean isMember;
    //hot 게시글 id
    private Long hotId;
    //hot 게시글 제목
    private String hotTitle;
    //hot 게시글 작성날짜
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime hotDate;
    //hot 게시글 하트수
    private Integer hotHeartNum;
    //hot 게시글 댓글 개수
    private Integer hotCommentNum;
    //hot 게시글 내용
    private String hotContent;
    //hot 게시글 작성자
    private String hotWriter;
    //hot 게시글 댓글
    private List<CommunityChatCommentDto> hotComments;
    //hot 게시글 게시글
    private List<CommunityPostDto> board;

    public CommunityChatResponseDto(final Boolean isMember,
                                    final Long hotId,
                                    final String hotTitle,
                                    final LocalDateTime hotDate,
                                    final Integer hotHeartNum,
                                    final Integer hotCommentNum,
                                    final String hotContent,
                                    final String hotWriter,
                                    final List<CommunityChatCommentDto> hotComments,
                                    final List<CommunityPostDto> board) {
        this.isMember = isMember;
        this.hotId = hotId;
        this.hotTitle = hotTitle;
        this.hotDate = hotDate;
        this.hotHeartNum = hotHeartNum;
        this.hotCommentNum = hotCommentNum;
        this.hotContent = hotContent;
        this.hotWriter = hotWriter;
        this.hotComments = hotComments;
        this.board = board;
    }
}
