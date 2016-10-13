package com.jayden;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;

/**
 * Created by Administrator on 2016/9/29.
 */
public class TestBeanFactoryPost2 implements BeanFactoryPostProcessor, Ordered {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("调用postProcessBeanFactory2");
        BeanDefinition bd = beanFactory.getBeanDefinition("person");
        MutablePropertyValues pv =  bd.getPropertyValues();
        String orgValue = pv.getPropertyValue("desc").getValue().toString();
        System.out.println("org value: " + orgValue);
        pv.removePropertyValue("desc");
        pv.addPropertyValue("desc", Util.append(orgValue, "BeanFactoryPostProcessor2"));
        System.out.println("set value: " + pv.getPropertyValue("desc").getValue());
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
