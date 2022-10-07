package com.bi.recordmanagement.auth;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpointAuthenticationFilter;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.lfs.auth.bus.LoginEventProducer;
//import com.lfs.auth.enums.ActivityType;
//import com.lfs.auth.enums.ConfigurationKeyImpl;
//import com.lfs.auth.enums.LfsExceptionEnum;
//import com.lfs.auth.enums.LoginStatus;
//import com.lfs.auth.exception.BadCredentialException;
//import com.lfs.auth.exception.InvalidOTPException;
//import com.lfs.auth.exception.UserNotVerifiedException;
//import com.lfs.auth.model.QUser;
//import com.lfs.auth.model.User;
//import com.lfs.auth.repositories.UserRepository;
//import com.lfs.auth.services.LoginSuccessAndFailService;
//import com.lfs.auth.services.MessageByLocaleService;
//import com.lfs.auth.services.TwoFactorService;
//import com.lfs.auth.utils.AuthUtility;
//import com.lfs.auth.utils.LFSConstant;
//import com.lfs.auth.vo.LoginEventVo;
//import com.lfs.config.ConfigurationService;

public class CustomAuthenticationFilter extends TokenEndpointAuthenticationFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationFilter.class);
    
//    @Autowired
//    private UserRepository userRepository;
    
//    @Autowired
//    private LoginSuccessAndFailService authSuccessAndFailService;
//   
//    @Autowired
//    private LoginEventProducer loginEventProducer;
//    
//    @Autowired
//	private ConfigurationService configurationService;
//    
//    @Autowired
//    private TwoFactorService twoFactorService;
//    
//    @Autowired
//    private MessageByLocaleService messageByLocaleService;
    
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, OAuth2RequestFactory oAuth2RequestFactory) {
        super(authenticationManager, oAuth2RequestFactory);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    	HttpServletRequest request = (HttpServletRequest)req;

    	super.doFilter(req,res,chain);
    }
    

    @Override
    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {
//        LOGGER.info("============User Oauth2 login success===============");
//        User user = (User) authResult.getPrincipal();
//        
//        if(!user.isVerify()) {
//        	try {
//				writeExceptionResponse(messageByLocaleService.getMessage("err.user.not.verify"), LfsExceptionEnum.USER_NOT_VERIFIED.getCode(), response);
//			}catch (Exception e) {
//				LOGGER.info("error in writing exception response for unverified User : {}",user.getLoginName());
//			}
//				
//    	}
//        
//        String otp = request.getParameter("otp");
//        if(null != user.getTwoFactorMode()) {
//        	if(StringUtils.isEmpty(otp)){
//        		try {
//        			twoFactorService.sendTwoFactor(user);
//        		} catch(Exception e) {
//        			try {
//						writeExceptionResponse(e.getMessage(), LfsExceptionEnum.USER_WEAK_CREDENTIALS.getCode(), response);
//					}catch (Exception e1) {
//						throw new UserNotVerifiedException(messageByLocaleService.getMessage("err.json.parse.error"));
//					}
//        		}
//        	} else {
//        		try {
//        			twoFactorService.verifyTwoFactor(user, otp);
//				} catch (InvalidOTPException e) {
//					try {
//						writeExceptionResponse(e.getMessage(), LfsExceptionEnum.BAD_CREDENTIALS.getCode(), response);
//					}catch (Exception e1) {
//						throw new InvalidOTPException(messageByLocaleService.getMessage("err.otp.not.valid"));
//					}
//				}
//    			
//    		}
//    	}
//        
//        Long configuredAttempts = configurationService.getConfigValue(Long.class, ConfigurationKeyImpl.OAUTH_LOGIN_LAST_ATTEMPTS_COUNT);
//        try {
//			CompletableFuture<User> res = authSuccessAndFailService.updateUserAttempts(user, Boolean.TRUE, request, response, configuredAttempts);
//			res.join();
//		} catch (InterruptedException e) {
//			LOGGER.error("execption is ",e);
//			Thread.currentThread().interrupt();
//
//		}
//        LoginEventVo loginEventVo = new LoginEventVo(AuthUtility.getRequestedIP(request),new Date()
//                ,user.getLoginName(),user.getId(),user.getAttempts(),user.getEmail(),LoginStatus.SUCCESS,ActivityType.LOGIN);
//        loginEventProducer.sendMessage(loginEventVo);
    }

    @Override
    protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
       
//    	LOGGER.info("============User Oauth2 login failed===============");
//
//    	String userName = request.getParameter("username");
//    	
//        Optional<User> userOp = userRepository.findOne(QUser.user.loginName.eq(userName).or(QUser.user.email.eq(userName)));
//        User user = userOp.isPresent() ? userOp.get() : null;
//    	
//        if(user != null) {
//    		
//        	Long configuredAttempts = configurationService.getConfigValue(Long.class, ConfigurationKeyImpl.OAUTH_LOGIN_LAST_ATTEMPTS_COUNT);
//    		setUserAttempts(user);
//			try {
//				CompletableFuture<User> res = authSuccessAndFailService.updateUserAttempts(user, Boolean.FALSE, request, response, configuredAttempts);
//				res.join();
//
//				//just send info for login status
//                LoginEventVo loginEventVo = new LoginEventVo(AuthUtility.getRequestedIP(request),new Date()
//                        ,user.getLoginName(),user.getId(),user.getAttempts(),user.getEmail(),LoginStatus.FAILED,ActivityType.LOGIN);
//                loginEventProducer.sendMessage(loginEventVo);
//
//			} catch (InterruptedException e) {
//				LOGGER.error("exception is {0}",e);
//				Thread.currentThread().interrupt();
//			}
//        	if(user.getAttempts()> Long.valueOf(configuredAttempts)) {
//        		 request.setAttribute("captcha",true);
//        	} else {
//        		request.setAttribute("captcha",false);
//        	}
//        	
//        }
        

    }

//	private void setUserAttempts(User user) {
//		Long attempts = user.getAttempts();
//		if(null != user.getAttempts()) {
//			user.setAttempts(attempts + 1);
//		} else {
//			user.setAttempts(1L);
//		}
//	}
	
	private Map setMap(String message, int code, HttpServletResponse response) {
		Map map = new HashMap();
		map.put("message",message);
		map.put("code",code);
		response.setContentType("application/json");
		return map;
	}
	
	
	private void writeExceptionResponse(String message, int code, HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException {
		Map map = setMap(message, code, response);	
		final ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), map);		
	}
}
