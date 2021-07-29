package springframework;

import cn.zgy.springframework.aop.AdvisedSupport;
import cn.zgy.springframework.aop.TargetSource;
import cn.zgy.springframework.aop.aspectj.AspectJExpressionPointcut;
import cn.zgy.springframework.aop.framework.Cglib2AopProxy;
import cn.zgy.springframework.aop.framework.JdkDynamicAopProxy;
import cn.zgy.springframework.core.io.DefaultResourceLoader;
import org.junit.Test;

import springframework.beans.IUserService;
import springframework.beans.UserService;
import springframework.beans.UserServiceInterceptor;

import java.lang.reflect.Method;


public class ApiTest {
    private DefaultResourceLoader resourceLoader;

    @Test
    public void test_aop() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* springframework.beans.UserService.*(..))");
        Class<UserService> clazz = UserService.class;
        Method method = clazz.getDeclaredMethod("queryUserInfo");

        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));
    }

    @Test
    public void test_dynamic() {
        //目标对象
        IUserService userService = new UserService();

        //组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* springframework.beans.IUserService.*(..))"));

        //代理对象(JdkDynamicAopProxy)
        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        //测试调用
        System.out.println("测试结果：" + proxy_jdk.queryUserInfo());

        // 代理对象(Cglib2AopProxy)
        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_cglib.register("彭于晏"));
    }
}
