package com.bi.recordmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bi.recordmanagement.models.AccessToken;

import java.util.Optional;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, String>, QuerydslPredicateExecutor<AccessToken> {


    @Query("SELECT token FROM AccessToken token " +
            "WHERE token.guid = :guid and token.userId= :userId ")
    Optional<AccessToken> findToken(@Param("guid") String guid, @Param("userId") Long userId);

}

