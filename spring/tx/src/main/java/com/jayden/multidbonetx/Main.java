package com.jayden.multidbonetx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/10/13.
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-multidbonetx.xml");
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
        ITestBiz testBiz = context.getBean(ITestBiz.class);

        testBiz.test();
    }
}
