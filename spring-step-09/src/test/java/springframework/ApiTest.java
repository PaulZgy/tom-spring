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
    public void test_factory_bean() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 调用代理方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }

    @Test
    public void test_prototype() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService01 = applicationContext.getBean("userService", UserService.class);
        UserService userService02 = applicationContext.getBean("userService", UserService.class);

        // 3. 配置 scope="prototype/singleton"
        System.out.println(userService01);
        System.out.println(userService02);

        // 4. 打印十六进制哈希
        System.out.println(userService01 + " 十六进制哈希：" + Integer.toHexString(userService01.hashCode()));

    }
}
