import org.quartz.Trigger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Map;

/**
 * Created by 089245 on 2017/6/13.
 */
public class AutoRegisterSchedulerFactoryBean extends SchedulerFactoryBean implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void afterPropertiesSet() throws Exception {
        ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
        Map<String, CronTriggerFactoryBean> beansOfType = listableBeanFactory.getBeansOfType(CronTriggerFactoryBean.class);
        setTriggers(beansOfType.values().stream().map(CronTriggerFactoryBean::getObject).toArray(Trigger[]::new));
        super.afterPropertiesSet();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

}
