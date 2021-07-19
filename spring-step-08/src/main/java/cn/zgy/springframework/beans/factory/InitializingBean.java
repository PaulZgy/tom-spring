package cn.zgy.springframework.beans.factory;

import cn.zgy.springframework.beans.BeansException;

public interface InitializingBean {

    /**
     * Bean属性填充之后调用
     * @throws BeansException
     */
    void afterPropertiesSet() throws BeansException;
}
