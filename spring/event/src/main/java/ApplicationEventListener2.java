import org.springframework.beans.factory.InitializingBean;

/**
 * Created by 089245 on 2017/7/10.
 */
public class ApplicationEventListener2 implements InitializingBean {

    public ApplicationEventListener2() {
        System.out.println("construct ApplicationEventListener2");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet ApplicationEventListener2");
    }
}
