package test;

import java.util.logging.Logger;

/**
 * @author sunyongjun
 * @since 2019/9/20
 */
public class WaitNotifyTest {
    private static Logger logger = Logger.getLogger(WaitNotifyTest.class.getName());
    private static final Object RESOURCE = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (RESOURCE) {
                sleep(2000);
                logger.info("wait thread-1 wait begin");
                try {
                    RESOURCE.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("wait thread-1 wait end");
                sleep(2000);
                logger.info("wait thread-1 end");
            }
        }).start();
        new Thread(() -> {
            synchronized (RESOURCE) {
                sleep(2000);
                logger.info("wait thread-2 wait begin");
                try {
                    RESOURCE.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("wait thread-2 wait end");
                sleep(2000);
                logger.info("wait thread-2 end");
            }
        }).start();

        sleep(5000);

        new Thread(() -> {
            synchronized (RESOURCE) {
                RESOURCE.notify();
//                RESOURCE.notifyAll();
                logger.info("notify-thread end");
            }
        }).start();
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
