package springframework;


import cn.zgy.springframework.beans.PropertyValue;
import cn.zgy.springframework.beans.PropertyValues;
import cn.zgy.springframework.beans.factory.config.BeanDefinition;
import cn.zgy.springframework.beans.factory.config.BeanReference;
import cn.zgy.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;
import springframework.beans.UserDao;
import springframework.beans.UserService;

public class ApiTest {
    @Test
    public void test_BeanFactory(){
        //1.初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //2.注册bean
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));
        //3.UserService设置属性uId、userDao
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", 10001));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));
        //4.UserService 注入bean
        beanFactory.registerBeanDefinition("userService", new BeanDefinition(UserService.class, propertyValues));
        //5.获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

    }
}
