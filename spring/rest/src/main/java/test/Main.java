package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 089245 on 2017/5/5.
 */
public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans.xml");
        RestService restService = context.getBean(RestService.class);
        restService.processJson();
        restService.processForm();
//        restService.createJson();
//        restService.createForm();

        logger.info("progress started!");
    }
}
