package com.bi.recordmanagement.services;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import com.bi.recordmanagement.config.Encoders;
import com.bi.recordmanagement.exception.ForbiddenException;
import com.bi.recordmanagement.exception.RMServiceException;
import com.bi.recordmanagement.repository.UserPassworRepo;
import com.bi.recordmanagement.repository.UserRepository;
import com.bi.recordmanagement.models.QUser;
import com.bi.recordmanagement.models.Role;
import com.bi.recordmanagement.models.User;
import com.bi.recordmanagement.models.UserPassword;
import com.bi.recordmanagement.utils.ContextUtil;
import com.bi.recordmanagement.utils.GMConstant;
import com.bi.recordmanagement.vo.ChangeUserPasswordVO;
import com.bi.recordmanagement.vo.UserUpdateVo;
import com.bi.recordmanagement.vo.UserVo;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanOperation;
import com.querydsl.core.util.ArrayUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Service
@Api(value = "CRUD Operation for UserService")
@Import(Encoders.class)
public class UserServiceImpl extends SearchServiceImpl<User, UserRepository>implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    // TODO OTP templates should be event based
    @Value("${otp.templateName:CustomerResetPasswordQA}")
    String templateName;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageByLocaleService messageByLocaleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder userPasswordEncoder;
    
    @Autowired
	private ContextUtil contextUtil;
    
    @Autowired
    private AuthorizationServerEndpointsConfiguration configuration;
    
    @Autowired
    @Qualifier("UserDetailsServiceImpl")
    private UserDetailsService userDetailsService;
  
    @Autowired
    private UserPassworRepo userPasswordRepo;

    @Override
    @PreAuthorize("@Auth.isValidClient(#userids)")
    public boolean deleteUsers(Long[] userids) {
        boolean isSuccess = false;
        List<Long> listUsers = new ArrayList<>(Arrays.asList(userids));
        if (!listUsers.isEmpty()) {
            List<User> userList = new ArrayList<>();
            getUsers(listUsers).forEach(user -> {
            	if (user != null && user.isEnabled()) {
            		user.setIsDeleted(user.getId());
            		userList.add(user);
            	}
            });
            List<User> savedUsers = userRepository.saveAll(userList);
            if (!savedUsers.isEmpty() && savedUsers.size() > 0) {
                isSuccess = true;
            }
            return isSuccess;
        } else {
            throw new EntityNotFoundException(messageByLocaleService.getMessage("err.user.not.exists", new String[]{String.valueOf(listUsers)}));
        }
    }

    @Override
    @ApiOperation(value = "Get user list by id")
    public User getUser(Long userId) {
    	
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(
                messageByLocaleService.getMessage("err.user.not.exists", new String[]{String.valueOf(userId)})));
        return user;
    }


    @Override
    @ApiOperation(value = "Get user list by ids")
    public List<User> getUsers(List<Long> users) {
        return userRepository.findAllById(users);
    }

    @Override
    @PreAuthorize("@Auth.isValidClient(#user)")
    public User updateUser(User user) {
    	
    	if(!user.isEnabled()) {
    		throw new EntityNotFoundException(messageByLocaleService.getMessage("err.user.not.enable"));
    	}
        return userRepository.saveAndFlush(user);
    }
    

    @Override
    public User createUser(User user, List<String> roleStr) throws RMServiceException {
    	List<Role> roles = roleService.getRole(roleStr);
    	user.setRoles(roles);
		user.setPassword(user.getPassword() != null ? userPasswordEncoder.encode(user.getPassword()): null);
		if(user.getCreatedOnDate() == null) {
			user.setCreatedOnDate(new Date());
		}
		User savedUser = userRepository.save(user);
		user.setId(savedUser.getId());
		return savedUser;
    }


    @Override
    public void changeUserPassword(ChangeUserPasswordVO user) throws ForbiddenException {
        User newUser = userRepository.findById(contextUtil.getUser().get().getId()).orElseThrow(() ->
                new EntityNotFoundException(messageByLocaleService.getMessage("err.user.notfound", user.getLoginName())));
        if (userPasswordEncoder.matches(user.getOldPassword(), newUser.getPassword())) {
            newUser.setPassword(userPasswordEncoder.encode(user.getNewPassword()));
            newUser.setLastModifiedDate(new Date());
            userRepository.saveAndFlush(newUser);
            //password should be changed only auth end.
            //gmcService.changePassword(user);
        } else {
            throw new ForbiddenException(messageByLocaleService.getMessage("err.user.old.password.notMatch", user.getLoginName()));
        }
    }

    private User getUser(String userName,String clientId) {
		BooleanExpression expression = QUser.user.clientId.eq(clientId).and(QUser.user.password.isNotNull());
    	return userRepository.findOne(expression).orElseThrow(()->  new BadCredentialsException("User name not found"));    
    }
	@Override
	public void resetUserPassword(final User user,String clientId) throws RMServiceException {
		User userNew = getUser(user.getLoginName(),clientId);
			
				userNew.setPassword(userPasswordEncoder.encode(user.getPassword()));
				user.setLastModifiedDate(new Date());
				userRepository.save(userNew);
	}

	private Optional<User> getUserByLoginName(final String loginName) {
		return userRepository.findOne(QUser.user.loginName.eq(loginName));
	}
	
	private Optional<User> getExistingUsers(BooleanExpression booleanExpression){
		return userRepository.findOne(booleanExpression);
	}
    public void checkAvailableUser(String userName) {
    	
    	if(!userRepository.existsUserByloginName(userName)) {
    		throw new EntityNotFoundException(messageByLocaleService.getMessage("err.clinic.user.name.not.exists"));
    	}
    }

	
	@Override
	public User addRole(Long userId , List<String> roles) {
		User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.user.not.exists", new String[] { String.valueOf(userId) })));
		if(!user.isEnabled()) {
			throw new EntityNotFoundException(messageByLocaleService.getMessage("err.user.not.enable"));
		}
		
		List<String> rolesToAdd = new ArrayList<>();
		for (String roleStr : roles) {
			boolean isMatch = false;
			for (Role role : user.getRoles()) {

				if (role.getName().equals(roleStr)) {
					isMatch = true;
					break;
				}
			}
			if (!isMatch) {
				rolesToAdd.add(roleStr);
			}
		}
		List<Role> roleList = null;
		if (!rolesToAdd.isEmpty()) {
			roleList = roleService.getRole(rolesToAdd);
			user.getRoles().addAll(roleList);
		}


		return userRepository.save(user);

	}
	
	@Override
	public User deleteRole(Long userId, List<String> roles) {
		User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.user.not.exists", new String[] { String.valueOf(userId) })));
		Iterator<Role> itr = user.getRoles().iterator();
		while (itr.hasNext()) {
			String dbRole = itr.next().getName();
			for (String role : roles) {
				if (role.equals(dbRole)) {
					itr.remove();
				} 
			}
		}
		return userRepository.save(user);

	}
	
	@Override
	public String generateAccessToken(String username) {

		Map<String, String> requestParameters = new HashMap<String, String>();
		Map<String, Serializable> extensionProperties = new HashMap<String, Serializable>();

		List<String> scopes =  new ArrayList<String>();
		scopes.add("ADMIN");
		List<String> roles  =  new ArrayList<String>();
		roles.add("admin");

		boolean approved = true;
		Set<String> responseTypes = new HashSet<String>();
		responseTypes.add("code");

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE" + roles));

		OAuth2Request oauth2Request = new OAuth2Request(requestParameters, "gmc-client", authorities, approved, new HashSet<String>(scopes),
				new HashSet<String>(Arrays.asList("api")), null, responseTypes, extensionProperties);

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, "N/A", authorities);
		OAuth2Authentication auth = new OAuth2Authentication(oauth2Request, authenticationToken);
		AuthorizationServerTokenServices tokenService = configuration.getEndpointsConfigurer().getTokenServices();
		OAuth2AccessToken token = tokenService.createAccessToken(auth);

		return token.getValue();
		
	}

	@Override
	public StringBuilder addScope(String userName, Set<String> scopeList) {
		StringBuilder scopeBuilder = new StringBuilder();
		User user = (User) userDetailsService.loadUserByUsername(userName);
		List<Role> rolesList = (List<Role>) user.getRoles();
		Iterator<String> itr = scopeList.iterator();
		while (itr.hasNext()) {
			String clientScope = itr.next();
			for (Role role : rolesList) {
				if(clientScope.equals(role.getName())) {
					scopeBuilder = scopeBuilder.append(role.getName()).append(" ");
					break;
				}
			}
		}
		return scopeBuilder;
	}
	
	public void disableUser(Long userId) {
		User user = userRepository.findOne(QUser.user.id.eq(userId)).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.user.not.exists", new String[] { String.valueOf(userId) })));;

		if(user.isEnabled()) {
			user.setEnabled(Boolean.FALSE);
		} else {
			user.setEnabled(Boolean.TRUE);
		}
		userRepository.save(user);
	}
	
	
	private boolean isUserExist(String userName, String platFormClientId) {
		BooleanExpression expression = QUser.user.clientId.eq(platFormClientId).and(QUser.user.password.isNotNull());
    	BooleanExpression loginNameExpress = QUser.user.loginName.eq(userName);
    	return userRepository.exists(expression);
	}

	@Override
	public User updateUser(UserUpdateVo userUpdateVo) {
		Optional<User> user = contextUtil.getUser();
		if (user.isPresent()) {
			User userDb = user.get();
			
			if (!StringUtils.isEmpty(userUpdateVo.getFirstName())) {
				userDb.setFirstName(userUpdateVo.getFirstName());
			}
			if (!StringUtils.isEmpty(userUpdateVo.getLastName())) {
				userDb.setLastName(userUpdateVo.getLastName());
			}
			if (userDb.getClientId().equals(GMConstant.GM_CLIENT) && !StringUtils.isEmpty(userUpdateVo.getEmail())) {
				userDb.setEmail(userUpdateVo.getEmail());
			}
			return userRepository.save(userDb);
		}
		return null;
	}

}
