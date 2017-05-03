package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by 089245 on 2017/5/2.
 */
public class CompletionServiceCompleteOneTest {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CompletionService<String> completionService = new ExecutorCompletionService<>(executor);
        int group = 3;
        Random random = new Random();
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < group; i++) {
            for (int j = 0; j < 2; j++) {
                final int finalI = i;
                final int finalJ = j;
                Future<String> future = completionService.submit(() -> {
                    String s = "";
                    int sleepSecond = 2 + random.nextInt(5);
                    Thread.sleep(sleepSecond * 1000);
                    s += (sleepSecond + "~");
                    if (random.nextBoolean()) {
                        s += null;
                    } else {
                        s += finalI + "~" + finalJ;
                    }
                    System.out.println(s);
                    return s;
                });
                futures.add(future);
            }
        }

        for (int i = 0; i < futures.size(); i++) {
            String s = completionService.take().get();
            if (!s.contains("null")) {
                System.out.println("result: " + s);
                break;
            }
        }
        for (int i = 0; i < futures.size(); i++) {
            System.out.println(futures.get(i).cancel(true));
        }

        executor.shutdown();

    }
}
