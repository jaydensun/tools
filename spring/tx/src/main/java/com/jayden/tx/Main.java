package com.jayden.tx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/10/13.
 */
public class Main {

    public static final int LOOP = 2;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-tx.xml");
//        System.out.println(context.getBeanDefinitionCount());
        ITestDao testDao = context.getBean("testDao", ITestDao.class);
//        Test test = new Test(2001, "aaa");
//        testDao.save(test);
//        Test test = new Test(1, 2002, "aaa");
//        testDao.save(test);
//        testDao.saveMultiTimes(test);
//        int id = 228;
//        testDao.update(id);
//        testDao.query(id);

//        test(testDao, testDao::saveBatch);
//        test(testDao, testDao::saveBatchSession);
//        test(testDao, testDao::saveBatchJdbc);
//
        test2(testDao, testDao::saveBatch2);
//        test2(testDao, testDao::saveBatchSession2);
//        test2(testDao, testDao::saveBatchJdbc2);
    }

    private static void test(ITestDao testDao, Runnable r) {
        for (int i = 0; i < LOOP; i++) {
            testDao.clear();
            r.run();
//            testDao.count();
        }
        System.out.println();
    }

    private static void test2(ITestDao testDao, Runnable r) {
        for (int i = 0; i < LOOP; i++) {
            testDao.clear2();
            r.run();
//            testDao.count2();
        }
        System.out.println();
    }
}
