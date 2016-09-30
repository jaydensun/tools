package com.jayden;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/9/29.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        System.out.println("===============下面输出结果============");
        System.out.println("person = " + context.getBean("person"));
        System.out.println("dog = " + context.getBean("dog"));
    }
}
