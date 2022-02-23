package com.AiFunding.ToBi.entity;

import com.AiFunding.ToBi.mapper.BoardRepository;
import com.AiFunding.ToBi.mapper.CommentRepository;
import com.AiFunding.ToBi.mapper.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BoardTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @AfterEach
    public void afterEach(){
        boardRepository.deleteAll();
        postRepository.deleteAll();
        commentRepository.deleteAll();
    }

    @Test
    @Transactional
    public void createBoard(){

        BoardEntity board = BoardEntity.builder()
                .postType("00").build();
        boardRepository.save(board);

        assertThat("00").isEqualTo(boardRepository.findAll().get(0).getPostType());
    }

    @Test
    @Transactional
    public void createPost(){
        PostEntity post = PostEntity.builder()
                .content("내용입니다.")
                .postLike(20)
                .title("제목입니다.")
                .build();

        postRepository.save(post);

        PostEntity findPost = postRepository.findAll().get(0);

        assertThat("내용입니다.").isEqualTo(findPost.getContent());
        assertThat("제목입니다.").isEqualTo(findPost.getTitle());
        assertThat(20).isEqualTo(findPost.getPostLike());
    }

    @Test
    @Transactional
    public void createComment(){
        CommentEntity comment = CommentEntity.builder()
                .commentLike(15)
                .content("내용입니다.").build();

        commentRepository.save(comment);

        CommentEntity findComment = commentRepository.findAll().get(0);

        assertThat("내용입니다.").isEqualTo(findComment.getContent());
        assertThat(15).isEqualTo(findComment.getCommentLike());
    }
}
