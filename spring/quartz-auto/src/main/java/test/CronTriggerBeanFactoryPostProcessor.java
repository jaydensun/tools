package test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 089245 on 2017/6/13.
 */
public class CronTriggerBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        boolean haveCronTriggerFactoryBean = false;
        Map<String, BeanDefinition> schedulerBeanDefinitions = new HashMap<>();
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
            try {
                Class<?> aClass = Class.forName(beanDefinition.getBeanClassName());
                if (SchedulerFactoryBean.class.isAssignableFrom(aClass)) {
                    schedulerBeanDefinitions.put(beanDefinitionName, beanDefinition);
                } else if (CronTriggerFactoryBean.class.isAssignableFrom(aClass)) {
                    haveCronTriggerFactoryBean = true;
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        if (schedulerBeanDefinitions.isEmpty()) {
            if (haveCronTriggerFactoryBean) {
                throw new RuntimeException("no SchedulerFactoryBean found");
            }
        } else if (schedulerBeanDefinitions.size() != 1) {
            throw new RuntimeException("multi SchedulerFactoryBean found: " + schedulerBeanDefinitions.keySet());
        }
    }
}
