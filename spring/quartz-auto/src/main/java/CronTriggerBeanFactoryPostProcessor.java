import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.*;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by 089245 on 2017/6/13.
 */
public class CronTriggerBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Map<String, BeanDefinition> schedulerBeanDefinitions = new HashMap<>();
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getBeanClassName().equals(SchedulerFactoryBean.class.getName())) {
                schedulerBeanDefinitions.put(beanDefinitionName, beanDefinition);
            }
        }
        if (schedulerBeanDefinitions.isEmpty()) {
            throw new RuntimeException("no SchedulerFactoryBean found");
        }
        if (schedulerBeanDefinitions.size() != 1) {
            throw new RuntimeException("multi SchedulerFactoryBean found: " + schedulerBeanDefinitions.keySet());
        }

        BeanDefinition schedulerBeanDefinition = schedulerBeanDefinitions.values().iterator().next();
        List<String> triggerBeanNames = new ArrayList<>();
        for (String s : beanFactory.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(s);
            if (beanDefinition.getBeanClassName().equals(CronTriggerFactoryBean.class.getName())) {
                triggerBeanNames.add(s);
            }
        }
        MutablePropertyValues propertyValues = schedulerBeanDefinition.getPropertyValues();
        ManagedList<BeanReference> list = triggerBeanNames.stream().map(RuntimeBeanReference::new).collect(Collectors.toCollection(ManagedList::new));
        propertyValues.add("triggers", list);
    }
}
