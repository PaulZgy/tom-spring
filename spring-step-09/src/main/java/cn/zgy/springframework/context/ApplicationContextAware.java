package cn.zgy.springframework.context;

import cn.zgy.springframework.beans.BeansException;
import cn.zgy.springframework.beans.factory.Aware;

public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
