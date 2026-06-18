package com.resumeanalyzer.repository;

import com.resumeanalyzer.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long>
{
    //find RefreshToken by token
    Optional<RefreshToken> findByToken(String token);

    // delete all tokens of a user on logout
    @Modifying
    @Query("DELETE FROM RefreshToken r WHERE r.user.id = :userId")
    void deleteAllByUserId(Long userId);

}
