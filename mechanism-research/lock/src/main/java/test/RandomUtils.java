package test;

import java.util.Random;

/**
 * @author sunyongjun
 * @since 2019/9/20
 */
public class RandomUtils {
    public static long nextInt(int i, int i1) {
        return i + new Random().nextInt(i1 - i);
    }

    public static long nextLong(int i, int i1) {
        return i + new Random().nextInt(i1- i);
    }
}
