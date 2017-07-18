import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/10/13.
 */
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        System.out.println(context.getBeanDefinitionCount());
//        context.start();
//        context.stop();
//        context.destroy();
    }
}
