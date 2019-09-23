package caller;

/**
 * @author sunyongjun
 * @since 2019/9/23
 */
public class Util {
    public static void printStackTrace(StackTraceElement[] stack) {
        for (int i = 0; i < stack.length; i++)
        {
            StackTraceElement s = stack[i];
            System.out.format(" ClassName:%d\t%s\n", i, s.getClassName());
            System.out.format("MethodName:%d\t%s\n", i, s.getMethodName());
            System.out.format("  FileName:%d\t%s\n", i, s.getFileName());
            System.out.format("LineNumber:%d\t%s\n\n", i, s.getLineNumber());
        }
    }

    static void getCallerFromThread()
    {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        printStackTrace(stack);
    }

    public static void getCallerFromThrowable()
    {
        StackTraceElement stack[] = (new Throwable()).getStackTrace();
        Util.printStackTrace(stack);
    }
}
