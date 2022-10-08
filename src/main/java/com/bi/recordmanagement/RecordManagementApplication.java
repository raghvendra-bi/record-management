package com.bi.recordmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@SpringBootApplication(scanBasePackages = "com.bi")
public class RecordManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecordManagementApplication.class, args);
	}
	
	public Module hibernate5Module() {
        return new Hibernate5Module();
    }
	
	@Bean
	public javax.validation.Validator validator() {
		final LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
		factory.setValidationMessageSource(messageSource());
		return factory;
	}
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("message");
//		messageSource.setParentMessageSource(new CustomMessageSource());
		 messageSource.setCacheSeconds(3600); //refresh cache once per hour
		return messageSource;
	}
    
    @RequestMapping(value = "/api/health")
    public ResponseEntity<String> health(@RequestParam String token) {
        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.name());
    }

}
