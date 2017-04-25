package test;

import org.apache.commons.io.IOUtils;
import org.xerial.snappy.SnappyOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2015/5/11 15:59.
 */
public class PicSnappyTest {
    public static void main(String[] args) throws InterruptedException, IOException {
        String path = "C:\\Users\\Administrator\\Desktop\\pic";
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream(100 * 1024 * 1024);
        SnappyOutputStream stream = new SnappyOutputStream(arrayOutputStream);
        byte[] buffer = new byte[1024 * 1024];
        File dir = new File(path);
        int totalLen = 0;
        for (File file : dir.listFiles()) {
            int read = IOUtils.read(new FileInputStream(file), buffer);
            totalLen += read;
            System.out.println(String.format("file: %s, length: %d", file, read));
            stream.write(buffer, 0, read);
        }
        stream.flush();

        System.out.println("original: " + totalLen);
        System.out.println("snappy  : " + arrayOutputStream.size());
    }

}
