package com.bi.recordmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
public class ConversionConfiguration {
	  @Bean
	    public ConversionService conversionService() {
	        final DefaultConversionService conversionService = new DefaultConversionService();
	        conversionService.addConverter(IAuthorizationExpression.class, Boolean.class, IAuthorizationExpression::mayProceed);
	        return conversionService;
	    }
}
	