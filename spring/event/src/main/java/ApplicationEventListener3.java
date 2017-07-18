import org.springframework.beans.factory.InitializingBean;

/**
 * Created by 089245 on 2017/7/10.
 */
public class ApplicationEventListener3 implements InitializingBean {

    public ApplicationEventListener3() {
        System.out.println("construct ApplicationEventListener3");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet ApplicationEventListener3");
    }
}
