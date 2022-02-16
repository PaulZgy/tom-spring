package cn.zgy.springframework.beans.factory.config;

import cn.zgy.springframework.beans.BeansException;
import cn.zgy.springframework.beans.factory.BeanFactory;


public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 执行BeanPostProcessors接口实现类的postProcessorBeforeInitialization方法
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * 执行BeanPostProcessors接口实现类的postProcessorAfterInitialization方法
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;

}
