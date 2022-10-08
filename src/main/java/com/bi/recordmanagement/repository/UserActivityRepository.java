package com.bi.recordmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bi.recordmanagement.models.UserActivity;

import io.swagger.annotations.Api;

@Transactional
@Repository
@Api(value = "UserActivity of user by id")
public interface UserActivityRepository extends JpaRepository<UserActivity, Long>, QuerydslPredicateExecutor<UserActivity> {

}
