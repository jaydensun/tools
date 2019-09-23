package caller;

/**
 * @author sunyongjun
 * @since 2019/9/23
 */
public class ThrowableStackTraceTest {
    public static void main(String[] args)
    {
        ThrowableStackTraceTest tc = new ThrowableStackTraceTest();
        tc.test();
    }

    private void test()
    {
        Util.getCallerFromThrowable();
    }

}
