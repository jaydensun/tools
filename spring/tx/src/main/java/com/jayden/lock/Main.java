package com.jayden.lock;

import org.hibernate.SQLQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/10/13.
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-lock.xml");
        ITestLockDao testLockDao = context.getBean(ITestLockDao.class);

        for (int i = 0; i < 10; i++) {
            final String machine = "machine" + i;
            final String module = "module" + i;
            execute(() -> {
                testLockDao.insert(module);
            }, 100);
            for (int j = 0; j < 4; j++) {
                execute(() -> {
                    while (true) {
                        testLockDao.mark(module, machine);
                        int count = testLockDao.running(module, machine);
                        if (count == 0) {
                            break;
                        }
                    }

                }, 10000);
            }
        }

        execute(testLockDao::clean, 60000);
    }

    private static void execute(Runnable runnable, int interval) {
        new Thread(() -> {
            while (true) {
                runnable.run();
                try {
                    TimeUnit.MILLISECONDS.sleep(interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
