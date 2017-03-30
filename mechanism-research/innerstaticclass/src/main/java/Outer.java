/**
 * Created by 089245 on 2017/3/30.
 */
public class Outer {

    public Outer() {
        System.out.println("init begin");
        throw new RuntimeException();
    }

    static class Holder {
        static Outer instance = new Outer();
    }

    public static Outer getInstance() {
        return Holder.instance;
    }

    public static void main(String[] args) {
        test();
        test();
    }

    private static void test() {
        try {
            getInstance();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
