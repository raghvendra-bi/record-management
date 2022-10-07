package com.bi.recordmanagement.auth;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.lfs.auth.utils.AuthUtility;

public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthExceptionEntryPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws ServletException {
		if (request.getServletPath().contains("/oauth/token")) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		} else if (request.getServletPath().contains("/error")) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}

		Map map = new HashMap();
//		map.put("requested", AuthUtility.getRequestedIP(request));
		map.put("error", response.getStatus());			
		map.put("message", authException.getMessage());
		
		if (request.getAttribute("captcha") != null) {
			map.put("captcha", request.getAttribute("captcha"));
		}
		if (request.getAttribute("code") != null) {
			map.put("code", request.getAttribute("code"));
		}
		map.put("path", request.getServletPath());
		map.put("timestamp", String.valueOf(new Date().getTime()));
		response.setContentType("application/json");
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(response.getOutputStream(), map);
		} catch (Exception e) {
			throw new ServletException();
		}
	}
}