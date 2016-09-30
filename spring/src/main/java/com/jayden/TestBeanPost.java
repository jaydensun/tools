package com.jayden;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by Administrator on 2016/9/29.
 */
public class TestBeanPost implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Person) {
            Person person = (Person) bean;
            person.desc = Util.append(person.desc, "postProcessBeforeInitialization");
        }
        System.out.println("postProcessBeforeInitialization|" + beanName + "|" + bean.toString());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Person) {
            Person person = (Person) bean;
            person.desc = Util.append(person.desc, "postProcessAfterInitialization");
        }
        System.out.println("postProcessAfterInitialization|" + beanName + "|" + bean.toString());
        return bean;
    }
}
