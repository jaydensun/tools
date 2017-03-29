/**
 * Created by 089245 on 2017/3/29.
 */
public class ThreadUtil {
    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
