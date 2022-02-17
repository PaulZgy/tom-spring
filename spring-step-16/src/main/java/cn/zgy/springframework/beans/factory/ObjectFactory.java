package cn.zgy.springframework.beans.factory;

import cn.zgy.springframework.beans.BeansException;

public interface ObjectFactory<T> {
    T getObject() throws BeansException;
}
