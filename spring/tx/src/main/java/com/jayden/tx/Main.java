package com.jayden.tx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/10/13.
 */
public class Main {

    public static final int LOOP = 5;

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
//        testDao.insert(id);
////
        test(testDao, testDao::saveBatch);
        test(testDao, testDao::saveBatchSession);
        test(testDao, testDao::saveBatchJdbc);

        test2(testDao, testDao::saveBatch2);
        test2(testDao, testDao::saveBatchSession2);
        test2(testDao, testDao::saveBatchJdbc2);

//        testDao.importRequestLog();

//        test3(testDao, testDao::saveBatchSession3);
//        test3(testDao, testDao::saveBatchJdbc3);
    }

    private static void test(ITestDao testDao, Runnable r) {
        for (int i = 0; i < LOOP; i++) {
            testDao.clear();
            if (oneLoop()) testDao.count();
            long start = System.currentTimeMillis();
            r.run();
            System.out.println(System.currentTimeMillis() - start);
            if (oneLoop()) testDao.count();
        }
        System.out.println();
    }

    private static boolean oneLoop() {
        return LOOP == 1;
    }

    private static void test2(ITestDao testDao, Runnable r) {
        for (int i = 0; i < LOOP; i++) {
            testDao.clear2();
            if (oneLoop()) testDao.count2();
            long start = System.currentTimeMillis();
            r.run();
            System.out.println(System.currentTimeMillis() - start);
            if (oneLoop())  testDao.count2();
        }
        System.out.println();
    }

    private static void test3(ITestDao testDao, Runnable r) {
        for (int i = 0; i < LOOP; i++) {
            testDao.clear3();
            if (oneLoop()) testDao.count2();
            long start = System.currentTimeMillis();
            r.run();
            System.out.println(System.currentTimeMillis() - start);
            if (oneLoop()) testDao.count2();
        }
        System.out.println();
    }
}
