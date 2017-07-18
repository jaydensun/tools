import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by 089245 on 2017/7/10.
 */
public class ApplicationEventListener implements InitializingBean, ApplicationListener<ApplicationEvent> {

    public ApplicationEventListener() {
        System.out.println("construct ApplicationEventListener");
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("onApplicationEvent: " + event.getClass());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet ApplicationEventListener");
    }
}
