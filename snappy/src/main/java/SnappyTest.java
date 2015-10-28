import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.xerial.snappy.SnappyInputStream;
import org.xerial.snappy.SnappyOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Administrator on 2015/5/11 15:59.
 */
public class SnappyTest {
    public static void main(String[] args) throws InterruptedException, IOException {
        writeSeparate();

        System.out.println();
        System.out.println();

        writeOnce();
    }

    private static void writeSeparate() throws IOException {
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream(100);
        SnappyOutputStream stream = new SnappyOutputStream(arrayOutputStream);

        byte[] ba = getBytes();

        stream.write(ba);
        stream.flush();
        System.out.println("first:\n" + Arrays.toString(arrayOutputStream.toByteArray()));

        stream.write(ba);
        stream.flush();
        System.out.println("second:\n" + Arrays.toString(arrayOutputStream.toByteArray()));

        byte[] origin = ArrayUtils.addAll(ba, ba);
        System.out.println("origin:\n" + Arrays.toString(origin));

        SnappyInputStream snappyInputStream = new SnappyInputStream(new ByteArrayInputStream(arrayOutputStream.toByteArray()));
        byte[] after = IOUtils.toByteArray(snappyInputStream);
        System.out.println("after:\n" + Arrays.toString(after));
        System.out.println("origin & after is equal:" + Arrays.equals(origin, after));
    }

    private static byte[] getBytes() {
        byte[] ba = new byte[20];
        Arrays.fill(ba, (byte) 'a');
        return ba;
    }

    private static void writeOnce() throws IOException {
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream(100);
        SnappyOutputStream stream = new SnappyOutputStream(arrayOutputStream);

        byte[] ba = getBytes();

        stream.write(ba);
        System.out.println("first:\n" + Arrays.toString(arrayOutputStream.toByteArray()));

        stream.write(ba);
        stream.flush();
        System.out.println("second:\n" + Arrays.toString(arrayOutputStream.toByteArray()));

        byte[] origin = ArrayUtils.addAll(ba, ba);
        System.out.println("origin:\n" + Arrays.toString(origin));

        SnappyInputStream snappyInputStream = new SnappyInputStream(new ByteArrayInputStream(arrayOutputStream.toByteArray()));
        byte[] after = IOUtils.toByteArray(snappyInputStream);
        System.out.println("after:\n" + Arrays.toString(after));
        System.out.println("origin & after is equal:" + Arrays.equals(origin, after));
    }

}
