package com.bi.recordmanagement.config;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.ConstructorResolver;
import org.springframework.expression.MethodFilter;
import org.springframework.expression.MethodResolver;
import org.springframework.expression.OperatorOverloader;
import org.springframework.expression.PropertyAccessor;
import org.springframework.expression.TypeComparator;
import org.springframework.expression.TypeConverter;
import org.springframework.expression.TypeLocator;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public abstract class ForwardingStandardEvaluationContext extends StandardEvaluationContext{

	protected abstract StandardEvaluationContext standardEvaluationContext();

    // @formatter:off
    @Override public void setRootObject(final Object rootObject, final TypeDescriptor typeDescriptor) { standardEvaluationContext().setRootObject(rootObject, typeDescriptor); }
    @Override public void setRootObject(final Object rootObject) { standardEvaluationContext().setRootObject(rootObject); }
    @Override public TypedValue getRootObject() { return standardEvaluationContext().getRootObject(); }
    @Override public void addConstructorResolver(final ConstructorResolver resolver) { standardEvaluationContext().addConstructorResolver(resolver); }
    @Override public boolean removeConstructorResolver(final ConstructorResolver resolver) { return standardEvaluationContext().removeConstructorResolver(resolver); }
    @Override public void setConstructorResolvers(final List<ConstructorResolver> constructorResolvers) { standardEvaluationContext().setConstructorResolvers(constructorResolvers); }
    @Override public List<ConstructorResolver> getConstructorResolvers() { return standardEvaluationContext().getConstructorResolvers(); }
    @Override public void addMethodResolver(final MethodResolver resolver) { standardEvaluationContext().addMethodResolver(resolver); }
    @Override public boolean removeMethodResolver(final MethodResolver methodResolver) { return standardEvaluationContext().removeMethodResolver(methodResolver); }
    @Override public void setMethodResolvers(final List<MethodResolver> methodResolvers) { standardEvaluationContext().setMethodResolvers(methodResolvers); }
    @Override public List<MethodResolver> getMethodResolvers() { return standardEvaluationContext().getMethodResolvers(); }
    @Override public void setBeanResolver(final BeanResolver beanResolver) { standardEvaluationContext().setBeanResolver(beanResolver); }
    @Override public BeanResolver getBeanResolver() { return standardEvaluationContext().getBeanResolver(); }
    @Override public void addPropertyAccessor(final PropertyAccessor accessor) { standardEvaluationContext().addPropertyAccessor(accessor); }
    @Override public boolean removePropertyAccessor(final PropertyAccessor accessor) { return standardEvaluationContext().removePropertyAccessor(accessor); }
    @Override public void setPropertyAccessors(final List<PropertyAccessor> propertyAccessors) { standardEvaluationContext().setPropertyAccessors(propertyAccessors); }
    @Override public List<PropertyAccessor> getPropertyAccessors() { return standardEvaluationContext().getPropertyAccessors(); }
    @Override public void setTypeLocator(final TypeLocator typeLocator) { standardEvaluationContext().setTypeLocator(typeLocator); }
    @Override public TypeLocator getTypeLocator() { return standardEvaluationContext().getTypeLocator(); }
    @Override public void setTypeConverter(final TypeConverter typeConverter) { standardEvaluationContext().setTypeConverter(typeConverter); }
    @Override public TypeConverter getTypeConverter() { return standardEvaluationContext().getTypeConverter(); }
    @Override public void setTypeComparator(final TypeComparator typeComparator) { standardEvaluationContext().setTypeComparator(typeComparator); }
    @Override public TypeComparator getTypeComparator() { return standardEvaluationContext().getTypeComparator(); }
    @Override public void setOperatorOverloader(final OperatorOverloader operatorOverloader) { standardEvaluationContext().setOperatorOverloader(operatorOverloader); }
    @Override public OperatorOverloader getOperatorOverloader() { return standardEvaluationContext().getOperatorOverloader(); }
    @Override public void setVariable(final String name, final Object value) { standardEvaluationContext().setVariable(name, value); }
    @Override public void setVariables(final Map<String, Object> variables) { standardEvaluationContext().setVariables(variables); }
    @Override public void registerFunction(final String name, final Method method) { standardEvaluationContext().registerFunction(name, method); }
    @Override public Object lookupVariable(final String name) { return standardEvaluationContext().lookupVariable(name); }
    @Override public void registerMethodFilter(final Class<?> type, final MethodFilter filter) throws IllegalStateException { standardEvaluationContext().registerMethodFilter(type, filter); }
    // @formatter:on
}
