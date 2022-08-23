package com.AiFunding.ToBi.service.community;

import com.AiFunding.ToBi.dto.IsSuccessDto;
import com.AiFunding.ToBi.dto.community.CommunityCommentDto;
import com.AiFunding.ToBi.entity.CommentEntity;
import com.AiFunding.ToBi.entity.CustomerInformationEntity;
import com.AiFunding.ToBi.entity.PostEntity;
import com.AiFunding.ToBi.mapper.CommentRepository;
import com.AiFunding.ToBi.mapper.CustomerInformationRepository;
import com.AiFunding.ToBi.mapper.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class CommunityCommentService {
    private final CommentRepository commentRepository;
    private final CustomerInformationRepository customerInformationRepository;
    private final PostRepository postRepository;

    public CommunityCommentService(CommentRepository commentRepository, CustomerInformationRepository customerInformationRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.customerInformationRepository = customerInformationRepository;
        this.postRepository = postRepository;
    }

    /**
     *
     * @param communityCommentDto
     * 댓글정보
     * @return
     * 저장여부
     */
    public IsSuccessDto storeComment(CommunityCommentDto communityCommentDto) {
        //사용자 ID
        Long customerId = communityCommentDto.getCustomerId();
        CustomerInformationEntity customerInfo = customerInformationRepository.getById(customerId);
        //게시판 ID
        Long boardId = communityCommentDto.getBoardId();
        //부모게시글 사용자 ID
        Long parentPostId = communityCommentDto.getParentId();
        //게시글 ID
        Long postId = communityCommentDto.getPostId();
        PostEntity post = postRepository.getById(postId);
        //댓글
        String content = communityCommentDto.getContent();
        commentRepository.save(new CommentEntity(null, content, 0, parentPostId, customerInfo, post));

        return new IsSuccessDto(true);
    }
}
