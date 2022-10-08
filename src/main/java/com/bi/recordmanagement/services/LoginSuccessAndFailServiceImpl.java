package com.bi.recordmanagement.services;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.bi.recordmanagement.models.User;
import com.bi.recordmanagement.repository.UserRepository;
import com.bi.recordmanagement.services.LoginSuccessAndFailService;

import io.swagger.annotations.Api;

@Service
@Api(value = "user attempts")
public class LoginSuccessAndFailServiceImpl implements LoginSuccessAndFailService {

	@Autowired
	private UserRepository userRepository;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginSuccessAndFailServiceImpl.class);
    
    @Override
    @Async
    public CompletableFuture<User> updateUserAttempts(User user, Boolean issuccuss, HttpServletRequest request, HttpServletResponse response, Long configuredAttempts) throws InterruptedException{
    	
    	if(issuccuss) {
    		LOGGER.info("user  ==> {} login successful",user.getUsername());
    		if(user.getAttempts()!= null && user.getAttempts()> configuredAttempts) {
    			String capchaResString = request.getParameter("g-recaptcha-response");
    			if(!StringUtils.isEmpty(capchaResString)) {	
//    				verifyCaptcha(capchaResString, request, response);
    				user.setAttempts(0L);
    			}
    		} else {
    			user.setAttempts(0L);
    		}
    	} else if(null != user.getAttempts()){
    		Long delayTime = 1000L;
    		if(user.getAttempts() <= configuredAttempts) {
    			if(user.getAttempts() > 3) {
    				LOGGER.info("going to sleep for ==> {}",(user.getAttempts() - 3) * delayTime +" ms");
        			Thread.sleep((user.getAttempts() - 3) * delayTime);
    			} else {
    				LOGGER.info("going to sleep for ==> {}",1000 +" ms");
        			Thread.sleep(1000);
    			}
    		} else {
    			Long maxSleeptime = 500000L;
    			LOGGER.info("going to sleep for ==> {}",maxSleeptime +" ms");
    			Thread.sleep(maxSleeptime);
    		}
    	} 
    	user = userRepository.save(user);
    	return CompletableFuture.completedFuture(user);
    }
}
