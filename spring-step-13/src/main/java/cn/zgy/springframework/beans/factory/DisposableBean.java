package cn.zgy.springframework.beans.factory;

import cn.zgy.springframework.beans.BeansException;

public interface DisposableBean {

    void destroy() throws BeansException;
}
