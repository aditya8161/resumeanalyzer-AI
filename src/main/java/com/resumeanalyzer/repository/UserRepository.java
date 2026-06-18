package com.resumeanalyzer.repository;

import com.resumeanalyzer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>
{
    //find the user by email
    Optional<User> findByEmail(String email);
//    UserDetails findByEmail(String email);

    //exits by email or not check
    boolean existsByEmail(String email);
}
