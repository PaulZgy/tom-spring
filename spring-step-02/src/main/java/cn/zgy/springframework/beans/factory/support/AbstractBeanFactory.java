package cn.zgy.springframework.beans.factory.support;

import cn.zgy.springframework.beans.BeansException;
import cn.zgy.springframework.beans.factory.BeanFactory;
import cn.zgy.springframework.beans.factory.config.BeanDefinition;


public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    public Object getBean(String name) throws BeansException {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
}
