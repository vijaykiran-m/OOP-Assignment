package com.vijaykiran.Social.Media.App.OOP.Assignment.Repository;

import com.vijaykiran.Social.Media.App.OOP.Assignment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);// Change return type to User
    User getById(Long userID);
    boolean existsByEmail(String email);
    boolean existsById(Long userID);
}
