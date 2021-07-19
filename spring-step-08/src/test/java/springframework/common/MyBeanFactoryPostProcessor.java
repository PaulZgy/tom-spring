package springframework.common;

import cn.zgy.springframework.beans.BeansException;
import cn.zgy.springframework.beans.PropertyValue;
import cn.zgy.springframework.beans.PropertyValues;
import cn.zgy.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.zgy.springframework.beans.factory.config.BeanDefinition;
import cn.zgy.springframework.beans.factory.config.BeanFactoryPostProcessor;
import cn.zgy.springframework.beans.factory.config.ConfigurableBeanFactory;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }
}
