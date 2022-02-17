package cn.zgy.springframework.context.support;

import cn.zgy.springframework.beans.BeansException;
import cn.zgy.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.zgy.springframework.beans.factory.config.BeanFactoryPostProcessor;
import cn.zgy.springframework.beans.factory.config.BeanPostProcessor;
import cn.zgy.springframework.beans.factory.config.ConfigurableBeanFactory;
import cn.zgy.springframework.context.ApplicationEvent;
import cn.zgy.springframework.context.ApplicationListener;
import cn.zgy.springframework.context.ConfigurableApplicationContext;
import cn.zgy.springframework.context.event.ApplicationEventMulticaster;
import cn.zgy.springframework.context.event.ContextClosedEvent;
import cn.zgy.springframework.context.event.ContextRefreshedEvent;
import cn.zgy.springframework.context.event.SimpleApplicationEventMulticaster;
import cn.zgy.springframework.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;


    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    public void refresh() throws BeansException {
        //1.创建BeanFactory，并加载BeanDefinition
        refreshBeanFactory();

        //2.获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        //3.添加ApplicationContextAwareProcessor，让继承自ApplicationContextAware的Bean对象都能感知所属的ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        //4.在Bean实例化之前，执行BeanFactoryPostProcessor(Invoke factory processors registered as beans in the context)
        invokeBeanFactoryPostProcessor(beanFactory);

        //5.BeanPostProcessor 需要提前于其他Bean对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        //6.初始化事件发布者
        initApplicaitonEventMulticaster();

        //7.注册事件监听器
        registerListeners();

        //8.提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();

        //9.发布容器刷新完成事件
        finishRefresh();
    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void initApplicaitonEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    @Override
    public void close() {
        //发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));

        //执行销毁单例bean的销毁方法
        getBeanFactory().destroySingletons();
    }

    private void invokeBeanFactoryPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(requiredType);
    }
}
