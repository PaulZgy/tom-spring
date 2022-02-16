package springframework;

import cn.zgy.springframework.aop.AdvisedSupport;
import cn.zgy.springframework.aop.TargetSource;
import cn.zgy.springframework.aop.aspectj.AspectJExpressionPointcut;
import cn.zgy.springframework.aop.framework.Cglib2AopProxy;
import cn.zgy.springframework.aop.framework.JdkDynamicAopProxy;
import cn.zgy.springframework.context.support.ClassPathXmlApplicationContext;
import cn.zgy.springframework.core.io.DefaultResourceLoader;
import org.junit.Test;

import springframework.beans.IUserService;
import springframework.beans.UserService;
import springframework.beans.UserServiceInterceptor;

import java.lang.reflect.Method;


public class ApiTest {

    @Test
    public void test_scan() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());

    }

}
