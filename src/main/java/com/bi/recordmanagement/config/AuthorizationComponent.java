package com.bi.recordmanagement.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bi.recordmanagement.models.User;
import com.bi.recordmanagement.repository.UserRepository;
import com.bi.recordmanagement.utils.AuthUtility;
import com.bi.recordmanagement.utils.ContextUtil;
import com.bi.recordmanagement.utils.GMConstant;


@Component("Auth")
public class AuthorizationComponent implements IAuthorizationComponent {

	@Autowired
	private AuthUtility authUtils;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ContextUtil contextUtil;
	
	@Override
	public boolean isValidClient(Long[] userId) {

		Boolean response = Boolean.FALSE;
		List<User> users = getUser(userId, userRepo);
		String clientId = contextUtil.getClientID();
		for (User user : users) {
			if(GMConstant.GM_ADMIN_CLIENT.equalsIgnoreCase(clientId)) {

				response = authUtils.checkScopeToclient(user, clientId);
			}else if(GMConstant.GMC_CLIENT.equalsIgnoreCase(clientId)){
				response = Boolean.TRUE;
			}
		}
		return response;
	}

	private List<User> getUser(Long[] userIds , UserRepository userRepo) {

		List<Long> listUsers = Arrays.asList(userIds);
		List<User> users = userRepo.findAllById(listUsers);
		return users;
	}

	@Override
	public boolean isValidClient(User user) {
		
		return isValidClient(new Long[] {user.getId()});
	}

}
