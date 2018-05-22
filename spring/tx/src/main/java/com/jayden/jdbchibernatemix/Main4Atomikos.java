package com.jayden.jdbchibernatemix;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/10/13.
 */
public class Main4Atomikos {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-jdbchibernatemix-atomikos.xml");
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
        ITestBiz testBiz = context.getBean(ITestBiz.class);

        testBiz.test();
    }
}
