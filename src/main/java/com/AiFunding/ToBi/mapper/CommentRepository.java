package com.AiFunding.ToBi.mapper;

import com.AiFunding.ToBi.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
