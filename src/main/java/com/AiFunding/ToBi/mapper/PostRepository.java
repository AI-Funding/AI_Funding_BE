package com.AiFunding.ToBi.mapper;

import com.AiFunding.ToBi.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
