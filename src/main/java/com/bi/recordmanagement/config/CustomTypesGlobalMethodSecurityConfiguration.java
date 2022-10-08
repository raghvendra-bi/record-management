package com.bi.recordmanagement.config;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.expression.TypeConverter;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.expression.spel.support.StandardTypeConverter;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.Authentication;

public abstract class CustomTypesGlobalMethodSecurityConfiguration extends GlobalMethodSecurityConfiguration{
	protected abstract ApplicationContext applicationContext();

	protected abstract ConversionService conversionService();

	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		final ApplicationContext applicationContext = applicationContext();
		final TypeConverter typeConverter = new StandardTypeConverter(conversionService());
		final DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler() {
			@Override
			public StandardEvaluationContext createEvaluationContextInternal(final Authentication authentication, final MethodInvocation methodInvocation) {
				final StandardEvaluationContext decoratedStandardEvaluationContext = super.createEvaluationContextInternal(authentication, methodInvocation);
				return new ForwardingStandardEvaluationContext() {
					@Override
					protected StandardEvaluationContext standardEvaluationContext() {
						return decoratedStandardEvaluationContext;
					}

					@Override
					public TypeConverter getTypeConverter() {
						return typeConverter;
					}
				};
			}
		};
		handler.setApplicationContext(applicationContext);
		return handler;
	}
}
