package com.bi.recordmanagement.services;

import java.util.concurrent.CompletableFuture;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bi.recordmanagement.models.User;

public interface LoginSuccessAndFailService {

	CompletableFuture<User> updateUserAttempts(User user, Boolean issuccuss, HttpServletRequest request, HttpServletResponse response, Long configuredAttempts) throws InterruptedException;

   
}

