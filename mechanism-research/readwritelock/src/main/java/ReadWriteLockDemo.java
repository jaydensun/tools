import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
//        doubleRead();
        doubleWrite();
//        readWrite();
//        writeRead();
    }

    private static void doubleRead() {
        Data data = new Data();
        Worker t1 = new Worker(data, true, 5000);
        Worker t2 = new Worker(data, true, 5000);
        t1.start();
        ThreadUtil.sleep(2000);
        t2.start();
    }

    private static void readWrite() {
        Data data = new Data();
        Worker t1 = new Worker(data, true, 5000);
        Worker t2 = new Worker(data, false, 5000);
        t1.start();
        ThreadUtil.sleep(2000);
        t2.start();
    }

    private static void doubleWrite() {
        Data data = new Data();
        Worker t1 = new Worker(data, false, 5000);
        Worker t2 = new Worker(data, false, 5000);
        t1.start();
        ThreadUtil.sleep(2000);
        t2.start();
    }

    private static void writeRead() {
        Data data = new Data();
        Worker t1 = new Worker(data, false, 5000);
        Worker t2 = new Worker(data, true, 5000);
        t1.start();
        ThreadUtil.sleep(2000);
        t2.start();
    }

    static class Worker extends Thread {
        Data data;
        boolean read;
        private int millis;

        public Worker(Data data, boolean read, int millis) {
            this.data = data;
            this.read = read;
            this.millis = millis;
        }

        public void run() {
            try {
                if (read) {
                    data.readData(millis);
                }
                else
                    data.writeData(millis);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class Data {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        Lock read = lock.readLock();
        Lock write = lock.writeLock();

        public void writeData(int millis) throws Exception {
            log(" writeData:begin ");
            write.lock();
            log(" writeData:lock ");
            Thread.sleep(millis);
            write.unlock();
            log(" writeData:unlock ");
        }

        public int readData(int millis) throws Exception {
            log(" readData :begin ");
            read.lock();
            log(" readData :lock ");
            Thread.sleep(millis);
            read.unlock();
            log(" readData:unlock ");
            return 1;
        }
    }

    private static void log(String s) {
        System.out.println(Thread.currentThread().hashCode()
                + s + sdf.format(new Date()));
    }
}
