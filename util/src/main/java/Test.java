import java.util.IllegalFormatException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by 089245 on 2017/1/13.
 */
public class Test {
    public static void main(String[] args) {
        final Exception[] e = {null};
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                e[0] = new IllegalArgumentException();
                countDownLatch.countDown();
            }
        }, "thread-1").start();

        try {
            countDownLatch.await();
            throw e[0];
        } catch (Exception e1) {
//            e1.printStackTrace();
            new RuntimeException(e1).printStackTrace();
        }
    }
}
