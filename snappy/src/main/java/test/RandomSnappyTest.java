package test;

import org.xerial.snappy.SnappyOutputStream;

import java.io.ByteArrayOutputStream;
import java.util.Random;

/**
 * Created by Administrator on 2015/5/11 15:59.
 */
public class RandomSnappyTest {

    private static Random random = new Random();

    private static byte[] getBytes() {
        byte[] bytes = new byte[990 * 1000];
        random.nextBytes(bytes);
        return bytes;
    }

    public static void main(String[] args) throws Exception {
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream(100 * 1024 * 1024);
        SnappyOutputStream stream = new SnappyOutputStream(arrayOutputStream);
        int totalLen = 0;
        for (int i = 0; i < 10; i++) {
            byte[] buffer = getBytes();
            totalLen += buffer.length;
            stream.write(buffer);
        }
        stream.flush();

        System.out.println("original: " + totalLen);
        System.out.println("snappy  : " + arrayOutputStream.size());
    }

}
