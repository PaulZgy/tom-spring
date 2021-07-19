package springframework;


import cn.hutool.core.io.IoUtil;
import cn.zgy.springframework.beans.PropertyValue;
import cn.zgy.springframework.beans.PropertyValues;
import cn.zgy.springframework.beans.factory.config.BeanDefinition;
import cn.zgy.springframework.beans.factory.config.BeanPostProcessor;
import cn.zgy.springframework.beans.factory.config.BeanReference;
import cn.zgy.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.zgy.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import cn.zgy.springframework.context.support.ClassPathXmlApplicationContext;
import cn.zgy.springframework.core.io.DefaultResourceLoader;
import cn.zgy.springframework.core.io.Resource;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.junit.Before;
import org.junit.Test;
import springframework.beans.UserDao;
import springframework.beans.UserService;
import springframework.common.MyBeanFactoryPostProcessor;
import springframework.common.MyBeanPostProcessor;

import java.io.IOException;
import java.io.InputStream;

public class ApiTest {
    private DefaultResourceLoader resourceLoader;

    @Test
    public void test_BeanFactoryPostProcessorAndBeanPostProcessor() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3.BeanDefinition 加载完成 & Bean实例化之前，修改 BeanDefinition 的属性值
        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        // 4.Bean实例化之后，修改Bean属性信息
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        // 5.获取bean对象调用方法
        UserService userService = (UserService) beanFactory.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }

    @Test
    public void test_xml() {
        //1.初始化BeanFactory
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.registerShutdownHook();

        //2.获取Bean对象调用方法
        UserService userService = (UserService) context.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
        System.out.println("ApplicationContext " + userService.getApplicationContext());
        System.out.println("BeanFactoryAware " + userService.getBeanFactory());
    }
}
