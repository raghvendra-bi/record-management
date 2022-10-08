package com.bi.recordmanagement.config;

import com.bi.recordmanagement.models.User;

public interface IAuthorizationComponent {
	boolean isValidClient(Long[] userId);
	boolean isValidClient(User user);

}
