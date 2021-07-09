package springframework;


import cn.zgy.springframework.beans.factory.config.BeanDefinition;
import cn.zgy.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;
import springframework.beans.UserService;

public class ApiTest {
    @Test
    public void test_BeanFactory(){
        //1.初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //2.注册bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        //3.获取bean
        UserService userService = (UserService) beanFactory.getBean("userService","tom");
        System.out.println(userService);
        userService.queryUserInfo();


    }
}
