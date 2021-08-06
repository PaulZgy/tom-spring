package cn.zgy.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import cn.zgy.springframework.beans.BeansException;
import cn.zgy.springframework.beans.factory.DisposableBean;
import cn.zgy.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;
    private final String beanName;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws BeansException {
        //1.实现接口DisposableBean
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        //配置信息destroy-method{判断是为了避免二次执行销毁}
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            try {
                Method method = bean.getClass().getMethod(destroyMethodName);
                method.invoke(bean);
            } catch (Exception e) {
                throw new BeansException(beanName + "invoke destroy-method failed ", e);
            }
        }
    }
}
