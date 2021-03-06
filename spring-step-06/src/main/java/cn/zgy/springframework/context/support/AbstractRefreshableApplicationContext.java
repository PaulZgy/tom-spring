package cn.zgy.springframework.context.support;

import cn.zgy.springframework.beans.BeansException;
import cn.zgy.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.zgy.springframework.beans.factory.support.DefaultListableBeanFactory;

public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
