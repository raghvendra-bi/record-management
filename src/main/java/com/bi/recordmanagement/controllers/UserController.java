package com.bi.recordmanagement.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.bi.recordmanagement.auth.JwtTokenProvider;
import com.bi.recordmanagement.models.ActivityType;
//import com.gm.auth2.enums.ConfigurationKeyImpl;
import com.bi.recordmanagement.models.LoginStatus;
import com.bi.recordmanagement.exception.ForbiddenException;
import com.bi.recordmanagement.exception.RMServiceException;
import com.bi.recordmanagement.exception.RMServiceValidationEcxception;
//import com.gm.auth2.model.Configuration;
import com.bi.recordmanagement.models.Role;
import com.bi.recordmanagement.models.RoleEnum;
import com.bi.recordmanagement.models.User;
//import com.gm.auth2.model.UserVerificationType;
import com.bi.recordmanagement.repository.UserRepository;
import com.bi.recordmanagement.services.MessageByLocaleService;
import com.bi.recordmanagement.services.RoleService;
import com.bi.recordmanagement.services.TokenService;
import com.bi.recordmanagement.services.UserService;
import com.bi.recordmanagement.utils.AuthUtility;
import com.bi.recordmanagement.utils.ContextUtil;
import com.bi.recordmanagement.utils.GMCAPIJSONInput;
import com.bi.recordmanagement.views.OauthUserView.OauthUserBasicView;
import com.bi.recordmanagement.views.OauthUserView.OauthUserDetailedView;
import com.bi.recordmanagement.views.PublisherView;
import com.bi.recordmanagement.views.UserViews;
import com.bi.recordmanagement.vo.ChangeUserPasswordVO;
import com.bi.recordmanagement.vo.LoginEventVo;
import com.bi.recordmanagement.vo.UserUpdateVo;
import com.bi.recordmanagement.vo.UserVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
//import javassist.tools.web.BadHttpRequest;

/**
 * curl gmc-client:password1234@localhost:8080/oauth/token -d grant_type=password -d username=admin -d password=admin1234
 */
@RestController
@Api(value = "User Registration and crud operation for user")
@Validated
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private DefaultTokenServices tokenServices;
	@Autowired
    private TokenService tokenService;
    @Autowired
    private MessageByLocaleService messageByLocaleService;
    
    
    @Autowired
    private RoleService roleService;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private ClientDetailsService clientDetailsService;
    

    @ApiOperation(value = "User Registration by admin", notes = GMCAPIJSONInput.API_USER_CREATE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully registered User"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 400, message = "server could not understand the request due to invalid syntax")})
    @PostMapping("/oauth/admin/users")
    @JsonView(UserViews.UserRegisterView.class)
	@PreAuthorize("hasAuthority('CREATE_USER')")
    public User registerUser(@Validated(UserViews.UserRegisterView.class) @RequestBody @JsonView(UserViews.UserRegisterView.class) @Valid User user) throws RMServiceException {
		user.setId(null);
    	createUserData(user);
		List<String> roles =addRoleToUser(user);
		if (user.getEmail() != null) {
			user.setIsEmailVerfied(true);
		}
		if (user.getPhone() != null && user.getPhoneCode() != null) {
			user.setIsPhoneVerfied(true);
		}
//        user = userService.createUser(user, true, roles);
		user = userService.createUser(user, roles);
        String acessToken= tokenProvider.generateToken(user);
        user.setAccessToken(acessToken);
		return user;
    }
    
    private List<String> addRoleToUser( User user) {
    	List<String> roles = new ArrayList<String>();
       	roles.add(RoleEnum.USER.toString());
		return roles;
	}

	private  User createUserData(User user) {
    	user.setCountryIATACode(user.getCountryIATACode());
        user.setEnabled(true);
        user.setAttempts(0l);
        if(StringUtils.isEmpty(user.getPhone())) {
        	user.setPhone(null);
        	user.setPhoneCode(null);
        }else {
            user.setPhoneCode(StringUtils.isEmpty(user.getPhoneCode()) ? "91":user.getPhoneCode());         
        }
        user.setEmail(StringUtils.isEmpty(user.getEmail())? null : user.getEmail() );
        user.setIsAutoGenerated(false);
		return user;
	}


    @ApiOperation(value = "Update User", notes = GMCAPIJSONInput.API_USER_ADD)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully registered User"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 400, message = "server could not understand the request due to invalid syntax")})
    @PostMapping("/oauth/users/edit")
    @JsonView(UserViews.UserBasicView.class)
    public UserVo updateUser(@Validated(UserViews.UserRegisterView.class) @RequestBody /*@JsonView(UserViews.UserRegisterView.class)*/ UserVo userVo) throws RMServiceException {
        User user = userService.getUser(userVo.getId());
        user.setPhone(userVo.getPhone());
        user = userService.updateUser(user);
        userVo.setId(user.getId());
        return userVo;
    }

    @ApiOperation(value = "change Password", notes = GMCAPIJSONInput.API_changePassword_CREATE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 400, message = "server could not understand the request due to invalid syntax")})
    @PostMapping("/oauth/change-password")
    //@JsonView(UserViews.UserPasswordResetView.class)
    public void changeUserPassword(
            @Valid @RequestBody ChangeUserPasswordVO user,
            @RequestHeader(value = "Authorization",required = true) String token) throws RMServiceValidationEcxception, ForbiddenException {

        LOGGER.debug("token {}",token);
        if (token.startsWith("Bearer ") || token.startsWith("bearer ")) {
            token = token.substring(7, token.length());
        } else {
            throw new InvalidTokenException("Token was not recognised");
        }
        tokenService.verifyJwtToken(token);
        userService.changeUserPassword(user);
    }

    @ApiOperation(value = "reset Password", notes = GMCAPIJSONInput.API_resetPassword_CREATE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 400, message = "server could not understand the request due to invalid syntax")})
    @PostMapping("/oauth/reset-password")
    @JsonView(UserViews.UserPasswordResetView.class)
    public void resetUserPassword(@Validated(UserViews.UserPasswordResetView.class) @RequestBody /*@JsonView(UserViews.UserPasswordResetView.class)*/ User user,@RequestHeader(value ="Platform-Client-Id") String clientId) throws RMServiceException {
        //otpService.generateOtp(otp);
        userService.resetUserPassword(user,clientId);
    }

    //TODO: as of now its not calling from frontend but must need to be called
    @GetMapping(value = "/oauth/logout")
    public void Logout(HttpServletRequest request, HttpServletResponse response) {
        String authorization = request.getHeader("Authorization");
        LOGGER.info("======================================authorization -> "+authorization);
        if (authorization != null && authorization.contains("Bearer") || authorization.contains("bearer")){
            String tokenId = authorization.substring("Bearer".length()+1);
            if(tokenId == null) {
            	tokenId = authorization.substring("bearer".length()+1);
            }
            Long userId = tokenService.getUserIdByToken(tokenId);
            tokenServices.setAccessTokenValiditySeconds(0);
            tokenServices.setRefreshTokenValiditySeconds(0);
            boolean isLoggedout = tokenServices.revokeToken(tokenId);
            if(!isLoggedout){
                return;
            }
            HttpSession session= request.getSession();
            SecurityContextHolder.clearContext();
            if(session != null) {
                session.invalidate();
            }
            LOGGER.info("isLoggedout --------------> "+isLoggedout);
            Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; ++i) {
                if (cookies[i].getName().equals("access_token")||
                        cookies[i].getName().equals("refresh_token")) {
                    //Cookie cookie = new Cookie("user", cookies[i].getValue());
                    cookies[i].setMaxAge(0);
                    response.addCookie(cookies[i]);
                    break;
                }
            }
            //publish message to queue
            User user = userService.getUser(userId);
            LoginEventVo loginEventVo = new LoginEventVo(AuthUtility.getRequestedIP(request),new Date()
                    ,user.getLoginName(),user.getId(),user.getAttempts(), LoginStatus.SUCCESS, ActivityType.LOGOUT);
//            try {
//                logoutEventProducer.sendMessage(loginEventVo);
//            } catch (JsonProcessingException e) {
//                LOGGER.error("logout event not generated : {} ",e.getMessage());
//            }
        }
    }

    @ApiOperation(value = "Delete Users")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully deleted users"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 400, message = "server could not understand the request due to invalid syntax")})
    @PostMapping("/oauth/users/delete")
    public boolean deleteUser(@RequestBody Long[] doctorIds,
                              @RequestHeader(value = "Authorization",required = true) String token) {
        LOGGER.debug("token {}",token);

        if (token.startsWith("Bearer ") || token.startsWith("bearer ") ) {
            token = token.substring(7, token.length());
        } else {
            throw new InvalidTokenException("Token was not recognised");
        }
        tokenService.verifyJwtToken(token);
        return userService.deleteUsers(doctorIds);
    }
    
    @ApiOperation(value ="Check User login name of Clinic")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
		@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
		@ApiResponse(code = 400, message = "server could not understand the request due to invalid syntax") })	
	@GetMapping("/oauth/users/userName")
	public void getUserName(@RequestParam(value="userName", required=true)String userName) {
		userService.checkAvailableUser(userName);
    }
    
    @ApiOperation(value = "Get User")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 400, message = "server could not understand the request due to invalid syntax")})
    // @PreAuthorize("#oauth2.hasScope('bar') and #oauth2.hasScope('read')")
    @GetMapping("/oauth/users/{userId}")
    @JsonView(OauthUserDetailedView.class)
    public User getUser(@PathVariable(value = "userId") Long userId) {
        return  userService.getUser(userId);
    }
    
    
    @ApiOperation(value = "Add Role")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 400, message = "server could not understand the request due to invalid syntax")})
    // @PreAuthorize("#oauth2.hasScope('bar') and #oauth2.hasScope('read')")
    @PostMapping("/oauth/users/{userId}/roles")
    @JsonView(OauthUserBasicView.class)
    public User addRole(@PathVariable(value = "userId") Long userId,@RequestBody List<String> roles){
        return  userService.addRole(userId,roles);
    }
    
	@ApiOperation(value = "Delete Role")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 400, message = "server could not understand the request due to invalid syntax") })
	@DeleteMapping("/oauth/users/{userId}/roles")
	@JsonView(OauthUserBasicView.class)
	public User deleteRole(@PathVariable(value = "userId") Long userId,
			@RequestParam(value = "roles", required = true) List<String> roles) {
		return userService.deleteRole(userId, roles);
	}

}
