package com.bi.recordmanagement.services;

import java.util.List;
import java.util.Set;

import com.bi.recordmanagement.exception.ForbiddenException;
import com.bi.recordmanagement.exception.RMServiceException;
import com.bi.recordmanagement.models.Role;
import com.bi.recordmanagement.models.User;
import com.bi.recordmanagement.utils.PaginationConfig;
import com.bi.recordmanagement.vo.ChangeUserPasswordVO;
import com.bi.recordmanagement.vo.UserUpdateVo;
import com.bi.recordmanagement.vo.UserVo;


public interface UserService {
	
	boolean deleteUsers(Long[] users);

    List<User> getUsers(List<Long> ids);
    
    User getUser(Long id);

    User updateUser(User user);

    User createUser(User user, List<String> roles) throws RMServiceException;

    void changeUserPassword(ChangeUserPasswordVO user) throws ForbiddenException;

    void resetUserPassword(final User user,String clientId) throws RMServiceException;

	void checkAvailableUser(String userName);

	User addRole(Long userId, List<String> roles);

	User deleteRole(Long userId, List<String> roles);

	String generateAccessToken(String username);

	StringBuilder addScope(String string, Set<String> scopeList);
	
	void disableUser(Long userName);

	User updateUser(UserUpdateVo userUpdateVo);


}
