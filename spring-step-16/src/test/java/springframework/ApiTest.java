package springframework;

import cn.zgy.springframework.aop.AdvisedSupport;
import cn.zgy.springframework.aop.TargetSource;
import cn.zgy.springframework.aop.aspectj.AspectJExpressionPointcut;
import cn.zgy.springframework.aop.framework.Cglib2AopProxy;
import cn.zgy.springframework.aop.framework.JdkDynamicAopProxy;
import cn.zgy.springframework.context.support.ClassPathXmlApplicationContext;
import cn.zgy.springframework.core.io.DefaultResourceLoader;
import org.junit.Test;

import springframework.beans.*;

import java.lang.reflect.Method;


public class ApiTest {

    @Test
    public void test_scan() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        Wife wife = applicationContext.getBean("wife", Wife.class);
        System.out.println("老公的媳妇：" + husband.queryWife());
        System.out.println("媳妇的老公：" + wife.queryHusband());
    }

}
