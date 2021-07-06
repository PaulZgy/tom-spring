package cn.zgy.springframework.beans.factory;

import cn.zgy.springframework.beans.BeansException;
import cn.zgy.springframework.beans.factory.config.AutowireCapableBeanFactory;
import cn.zgy.springframework.beans.factory.config.BeanDefinition;
import cn.zgy.springframework.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
