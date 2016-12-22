package com.jayden.tx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/10/13.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-tx.xml");
//        System.out.println(context.getBeanDefinitionCount());
        ITestDao testDao = context.getBean("testDao", ITestDao.class);
        Test test = new Test(200);
//        testDao.save(test);
//        int id = 228;
//        testDao.update(id);
//        testDao.query(id);

        testDao.saveBatchJdbc();
        testDao.saveBatchJdbc();
        testDao.saveBatchJdbc();
        testDao.saveBatchJdbc();
//
//        testDao.saveBatchSession();
//        testDao.saveBatchSession();
//        testDao.saveBatchSession();

        testDao.saveBatch();
        testDao.saveBatch();
        testDao.saveBatch();
        testDao.saveBatch();

    }
}
