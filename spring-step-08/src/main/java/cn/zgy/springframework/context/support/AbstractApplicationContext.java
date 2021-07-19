package cn.zgy.springframework.context.support;

import cn.zgy.springframework.beans.BeansException;
import cn.zgy.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.zgy.springframework.beans.factory.config.BeanFactoryPostProcessor;
import cn.zgy.springframework.beans.factory.config.BeanPostProcessor;
import cn.zgy.springframework.beans.factory.config.ConfigurableBeanFactory;
import cn.zgy.springframework.context.ConfigurableApplicationContext;
import cn.zgy.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        getBeanFactory().destroySingletons();
    }

    public void refresh() throws BeansException {
        //1.创建BeanFactory，并加载BeanDefinition
        refreshBeanFactory();

        //2.获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        //3.在Bean实例化之前，执行BeanFactoryPostProcessor(Invoke factory processors registered as beans in the context)
        invokeBeanFactoryPostProcessor(beanFactory);

        //4.BeanPostProcessor 需要提前于其他Bean对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        //5.提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();

    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

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
}
