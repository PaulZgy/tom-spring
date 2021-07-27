package cn.zgy.springframework.context;

import cn.zgy.springframework.beans.factory.HierarchicalBeanFactory;
import cn.zgy.springframework.beans.factory.ListableBeanFactory;
import cn.zgy.springframework.core.io.ResourceLoader;

public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
