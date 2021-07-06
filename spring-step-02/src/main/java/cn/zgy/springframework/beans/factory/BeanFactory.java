package cn.zgy.springframework.beans.factory;

import cn.zgy.springframework.beans.BeansException;

public interface BeanFactory {
    Object getBean(String name) throws BeansException;
}
