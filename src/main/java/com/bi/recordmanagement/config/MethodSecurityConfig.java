package com.bi.recordmanagement.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = false)
public class MethodSecurityConfig extends CustomTypesGlobalMethodSecurityConfiguration {
    
	private final ApplicationContext applicationContext;
    private final ConversionService conversionService;
    
	public MethodSecurityConfig(
	            @Autowired final ApplicationContext applicationContext,
	            @Autowired final ConversionService conversionService
	    ) {
	        this.applicationContext = applicationContext;
	        this.conversionService = conversionService;
	    }
	@Override
	protected ApplicationContext applicationContext() {
		return applicationContext;
	}
	@Override
	protected ConversionService conversionService() {
		return conversionService;
	}

}