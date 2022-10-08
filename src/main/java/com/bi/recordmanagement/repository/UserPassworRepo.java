package com.bi.recordmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.bi.recordmanagement.models.UserActivity;
import com.bi.recordmanagement.models.UserPassword;

@Repository
public interface UserPassworRepo  extends JpaRepository<UserPassword, Long>, QuerydslPredicateExecutor<UserActivity> {

}
