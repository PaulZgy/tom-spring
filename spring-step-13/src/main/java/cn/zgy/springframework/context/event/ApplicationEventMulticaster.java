package cn.zgy.springframework.context.event;

import cn.zgy.springframework.context.ApplicationContext;
import cn.zgy.springframework.context.ApplicationEvent;
import cn.zgy.springframework.context.ApplicationListener;

public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);
}
