package cn.zgy.springframework.beans.factory;

import cn.zgy.springframework.beans.BeansException;

public interface BeanFactory {
    Object getBean(String name) throws BeansException;
    //新增,以便有参构造方法对象的实例化
    Object getBean(String name, Object... args) throws BeansException;
    //按类型获取bean
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

    <T> T getBean(Class<T> requiredType) throws BeansException;
}
