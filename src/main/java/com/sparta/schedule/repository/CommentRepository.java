package com.sparta.schedule.repository;

import com.sparta.schedule.entity.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
