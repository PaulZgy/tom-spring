package springframework.common;

import cn.zgy.springframework.beans.BeansException;
import cn.zgy.springframework.beans.factory.config.BeanPostProcessor;
import springframework.beans.UserService;

public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("userService")) {
            UserService userService = (UserService)bean;
            userService.setLocation("改为：杭州");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
