package com.vijaykiran.Social.Media.App.OOP.Assignment.Repository;

import com.vijaykiran.Social.Media.App.OOP.Assignment.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByDateDesc();
}

