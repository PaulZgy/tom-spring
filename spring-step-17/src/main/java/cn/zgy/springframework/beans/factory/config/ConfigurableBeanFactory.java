package cn.zgy.springframework.beans.factory.config;

import cn.zgy.springframework.beans.factory.HierarchicalBeanFactory;
import cn.zgy.springframework.util.StringValueResolver;
import com.sun.istack.internal.Nullable;
import cn.zgy.springframework.core.convert.ConversionService;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void destroySingletons();

    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    String resolveEmbeddedValue(String value);

    void setConversionService(ConversionService conversionService);

    @Nullable
    ConversionService getConversionService();
}
