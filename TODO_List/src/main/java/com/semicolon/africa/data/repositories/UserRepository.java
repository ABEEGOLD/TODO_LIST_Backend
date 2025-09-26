package com.semicolon.africa.data.repositories;

import com.semicolon.africa.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByUserId(Long userId);



}
