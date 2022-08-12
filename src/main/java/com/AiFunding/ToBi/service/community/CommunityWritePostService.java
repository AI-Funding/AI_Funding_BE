package com.AiFunding.ToBi.service.community;

import com.AiFunding.ToBi.dto.IsSuccessDto;
import com.AiFunding.ToBi.entity.BoardEntity;
import com.AiFunding.ToBi.entity.CustomerInformationEntity;
import com.AiFunding.ToBi.entity.PostEntity;
import com.AiFunding.ToBi.mapper.BoardRepository;
import com.AiFunding.ToBi.mapper.CustomerInformationRepository;
import com.AiFunding.ToBi.mapper.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommunityWritePostService {
    private PostRepository postRepository;
    private CustomerInformationRepository customerInformationRepository;
    private BoardRepository boardRepository;

    public CommunityWritePostService(PostRepository postRepository, CustomerInformationRepository customerInformationRepository, BoardRepository boardRepository) {
        this.postRepository = postRepository;
        this.customerInformationRepository = customerInformationRepository;
        this.boardRepository = boardRepository;
    }

    public IsSuccessDto storeWritePost(Long customerId, Long boardId, String title, String content) {
        //회원정보와 게시판 정보를 가져옴
        Optional<CustomerInformationEntity> optionalCustomerInformation = customerInformationRepository.findById(customerId);
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(boardId);
        //사용자나 게시판 정보가 존재하지 않는 경우 실패반환
        if (optionalCustomerInformation.isEmpty() || optionalBoardEntity.isEmpty()) return new IsSuccessDto(false);
        else {//성공시 게시글 저장 및 성공 반환
            postRepository.save(new PostEntity(null, content, title, 0, optionalCustomerInformation.get(), optionalBoardEntity.get(), null));
            return new IsSuccessDto(true);
        }
    }
}
