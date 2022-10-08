package com.bi.recordmanagement.repository;

import io.swagger.annotations.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bi.recordmanagement.models.User;

import java.util.List;
import java.util.Optional;


@Transactional
@Repository("userRepository")
@Api(value = "Fetches record of User by login id")
public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User> {
    @Query("SELECT DISTINCT user FROM User user " +
            "INNER JOIN FETCH user.roles AS roles " +
            "WHERE user.loginName = :userName")
        // @EntityGraph(attributePaths= "clinic.facilities")
    User findByUsername(@Param("userName") String username);

    //@EntityGraph(attributePaths= "clinic.facilities")
    Optional<User> findById(Long id);

    boolean existsUserByloginName(String userName);

    @Query("SELECT user from User user order by id DESC")
    List<User> findTopByOrderByIdDesc();

}