package com.bi.recordmanagement.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Component;

import com.bi.recordmanagement.models.Role;
import com.bi.recordmanagement.models.User;
import com.bi.recordmanagement.services.TokenService;

@Component
public class AuthUtility {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthUtility.class);

    private static final String key = "aesEncryptionKey";
    private static final String initVector = "encryptionIntVec";

    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private ClientDetailsService clientDetailsService;
    
    public static String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public static String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String getRequestedIP(HttpServletRequest request) {
        //String ip = "150.242.65.251";
    	String ip = null;
        String forwardedIp = request.getHeader("x-forwarded-for");
        if(null != forwardedIp)
        	ip = forwardedIp.split(",")[0];
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
            InetAddress inetAddress = null;
            try {
                inetAddress = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            String ipAddress = inetAddress.getHostAddress();
            ip = ipAddress;
        }
        LOGGER.info("requested login with IP address =>  {}",ip);
        return ip;
    }

    public void validateToken(String token) {
		if (token.startsWith("Bearer ")) {
            token = token.substring(7, token.length());
        } else {
            throw new InvalidTokenException("Token was not recognised");
        }
        tokenService.verifyJwtToken(token);
	}


	public boolean checkScopeToclient(User user, String clientId) {
		ClientDetails clientDetails= clientDetailsService.loadClientByClientId(clientId);
		Set<String> scopes = clientDetails.getScope();
		for (Role role : user.getRoles()) {
			if(!scopes.contains(role.getName())) {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}
}
