package cn.zgy.springframework.context.event;

import cn.zgy.springframework.beans.factory.BeanFactory;
import cn.zgy.springframework.context.ApplicationEvent;
import cn.zgy.springframework.context.ApplicationListener;
import cn.zgy.springframework.context.support.AbstractApplicationContext;

public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
