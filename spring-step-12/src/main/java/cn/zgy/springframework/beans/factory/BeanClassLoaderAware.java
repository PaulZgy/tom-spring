package cn.zgy.springframework.beans.factory;

import cn.zgy.springframework.beans.BeansException;

public interface BeanClassLoaderAware {

    void setBeanClassLoader(ClassLoader classLoader) throws BeansException;
}
