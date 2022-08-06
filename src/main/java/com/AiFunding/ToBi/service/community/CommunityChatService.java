package com.AiFunding.ToBi.service.community;

import com.AiFunding.ToBi.dto.Home.UserRequestDto;
import com.AiFunding.ToBi.dto.community.CommunityPostDto;
import com.AiFunding.ToBi.dto.community.CommunityCommentDto;
import com.AiFunding.ToBi.dto.community.chat.CommunityChatResponseDto;
import com.AiFunding.ToBi.entity.BoardEntity;
import com.AiFunding.ToBi.entity.CommentEntity;
import com.AiFunding.ToBi.entity.PostEntity;
import com.AiFunding.ToBi.mapper.BoardRepository;
import com.AiFunding.ToBi.mapper.CustomerInformationRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommunityChatService {
    private final BoardRepository boardRepository;
    private final CustomerInformationRepository customerInformationRepository;

    public CommunityChatService(BoardRepository boardRepository, CustomerInformationRepository customerInformationRepository) {
        this.boardRepository = boardRepository;
        this.customerInformationRepository = customerInformationRepository;
    }

    public CommunityChatResponseDto findCommunityChatBoard(Long id, String loginType) {
        //회원 존재여부
        boolean isMember = isMember(id, loginType);
        if(!isMember) return new CommunityChatResponseDto(false, null, null, null, null, null, null, null, null, null);


        //잡담 게시판 Entity
        String boardName = "잡담";
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findByPostType(boardName);
        optionalBoardEntity.orElseThrow(NullPointerException::new);
        //잡담 게시판에 있는 게시글 리스트
        List<PostEntity> postEntities = optionalBoardEntity.get().getPosts();
        //하트수가 가장 많은 게시글 찾음
        PostEntity maxLikePostEntity = null;
        int maxLike = 0;
        for (PostEntity postEntity : postEntities) {
            if (postEntity.getPostLike() > maxLike) {
                maxLike = postEntity.getPostLike();
                maxLikePostEntity = postEntity;
            }
        }

        //hot 게시글 id -> 하루 중 가장 하트가 많은 게시글
        Long hotId = maxLikePostEntity.getId();
        //hot 게시글 제목
        String hotTitle = maxLikePostEntity.getTitle();
        //hot 게시글 작성날짜
        LocalDateTime hotDate = maxLikePostEntity.getCreateAt();
        //hot 게시글 하트수
        int hotHeartNum = maxLike;
        //hot 게시글 댓글 개수
        int hotCommentNum = maxLikePostEntity.getComments().size();
        //hot 게시글 내용
        String hotContent = maxLikePostEntity.getContent();
        //hot 게시글 작성자
        String hotWriter = maxLikePostEntity.getCustomer().getNickname();
        //hot 게시글 댓글
        List<CommunityCommentDto> hotComments = getCommentInfoList(maxLikePostEntity);
        //게시글
        List<CommunityPostDto> board = getCommunityPostDtoList(optionalBoardEntity.get());

        return new CommunityChatResponseDto(true, hotId, hotTitle, hotDate, hotHeartNum, hotCommentNum, hotContent, hotWriter, hotComments, board);
    }

    /**
     * 게시판 정보를 받아서 게시글 정보를 넘겨줌
     * @param boardEntity 게시판 정보
     * @return 게시판의 게시글 List 반환
     */
    public List<CommunityPostDto> getCommunityPostDtoList(BoardEntity boardEntity) {
        List<CommunityPostDto> communityCommentDtoList = new ArrayList<>();

        //게시판에 있는 게시글 List
        List<PostEntity> postEntityList = boardEntity.getPosts();
        for (PostEntity postEntity : postEntityList) {
            //게시글 id
            Long id = postEntity.getId();
            //글 제목
            String title = postEntity.getTitle();
            //작성날짜
            LocalDateTime date = postEntity.getCreateAt();
            //하트 수
            Integer heartNum = postEntity.getPostLike();
            //댓글 개수
            Integer commentNum = postEntity.getComments().size();
            //내용
            String content = postEntity.getContent();
            //작성자
            String writer = postEntity.getCustomer().getNickname();
            //댓글
            List<CommunityCommentDto> comments = getCommentInfoList(postEntity);

            communityCommentDtoList.add(new CommunityPostDto(id, title, date, heartNum, commentNum, content, writer, comments));

        }
        return communityCommentDtoList;
    }

    /**
     * 게시글에 대한 모든 댓글 정보를 List에 담아 반환
     * @param postEntity
     * @return
     */
    public List<CommunityCommentDto> getCommentInfoList(PostEntity postEntity) {
        List<CommunityCommentDto> communityCommentDtoList = new ArrayList<>();
        //게시글에 대한 모든 댓글
        List<CommentEntity> commentEntityList = postEntity.getComments();
        for (CommentEntity commentEntity : commentEntityList) {
            //댓글 작성자
            String commentWriter = commentEntity.getCustomer().getNickname();
            //댓글 내용
            String commentContent = commentEntity.getContent();
            //댓글 작성 날짜
            LocalDateTime commentDate = commentEntity.getCreateAt();
            //댓글 좋아요 수
            Integer commentHeartNum = commentEntity.getCommentLike();

            communityCommentDtoList.add(new CommunityCommentDto(commentWriter, commentContent, commentDate, commentHeartNum));
        }
        return communityCommentDtoList;
    }

    public boolean isMember(Long id, String loginType) {
        return customerInformationRepository.existsByIdAndLoginType(id, loginType);
    }
}
