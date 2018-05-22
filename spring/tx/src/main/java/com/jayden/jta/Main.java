package com.jayden.jta;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Random;

/**
 * Created by 089245 on 2017/10/18.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-jta.xml");
        ITestJtaBiz testJtaBiz = context.getBean(ITestJtaBiz.class);
        testJtaBiz.insert(new Random().nextInt(1000));
    }
}
