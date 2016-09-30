package com.jayden;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

/**
 * Created by Administrator on 2016/9/29.
 */
public class TestBeanPost2 implements BeanPostProcessor, Ordered {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Person) {
            Person person = (Person) bean;
            person.desc = Util.append(person.desc, "postProcessBeforeInitialization2");
        }
        System.out.println("postProcessBeforeInitialization2|" + beanName + "|" + bean.toString());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Person) {
            Person person = (Person) bean;
            person.desc = Util.append(person.desc, "postProcessAfterInitialization2");
        }
        System.out.println("postProcessAfterInitialization2|" + beanName + "|" + bean.toString());
        return bean;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
