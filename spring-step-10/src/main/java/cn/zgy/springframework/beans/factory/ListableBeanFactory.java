package cn.zgy.springframework.beans.factory;

import cn.zgy.springframework.beans.BeansException;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory {

    /**
     * 按照类型返回bean
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回注册表中所有的bean名称
     * @return
     */
    String[] getBeanDefinitionNames();

}
