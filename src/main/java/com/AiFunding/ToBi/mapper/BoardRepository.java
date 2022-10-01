package com.AiFunding.ToBi.mapper;

import com.AiFunding.ToBi.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    public Optional<BoardEntity> findById(Long id);
    public Optional<BoardEntity> findByPostType(String postType);
}
