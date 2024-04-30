package com.vijaykiran.Social.Media.App.OOP.Assignment.Repository;

import com.vijaykiran.Social.Media.App.OOP.Assignment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
