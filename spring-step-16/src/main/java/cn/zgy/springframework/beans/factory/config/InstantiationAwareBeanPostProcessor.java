package cn.zgy.springframework.beans.factory.config;

import cn.zgy.springframework.beans.BeansException;
import cn.zgy.springframework.beans.PropertyValue;
import cn.zgy.springframework.beans.PropertyValues;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException;

    boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException;

    default Object getEarlyBeanReference(Object bean, String beanName) {
        return bean;
    }
}
