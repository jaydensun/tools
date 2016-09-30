package com.jayden;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * Created by Administrator on 2016/9/29.
 */
public class TestBeanFactoryPost implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("调用postProcessBeanFactory");
        BeanDefinition bd = beanFactory.getBeanDefinition("person");
        MutablePropertyValues pv =  bd.getPropertyValues();
        String orgValue = pv.getPropertyValue("desc").getValue().toString();
        System.out.println("org value: " + orgValue);
        pv.removePropertyValue("desc");
        pv.addPropertyValue("desc", Util.append(orgValue, "BeanFactoryPostProcessor"));
        System.out.println("set value: " + pv.getPropertyValue("desc").getValue());
    }
}
