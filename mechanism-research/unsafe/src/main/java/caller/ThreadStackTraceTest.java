package caller;

/**
 * @author sunyongjun
 * @since 2019/9/23
 */
public class ThreadStackTraceTest {
    public static void main(String[] args) {
        ThreadStackTraceTest tc = new ThreadStackTraceTest();
        tc.test();
    }

    private void test() {
        Util.getCallerFromThread();
    }

}