package test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by 089245 on 2017/5/2.
 */
public class CompletionServiceTest {
    public static void main(String[] args) throws Exception {
        CompletionService<String> completionService = new ExecutorCompletionService<>(Executors.newFixedThreadPool(10));
        int group = 0;
        for (int i = 0; i < 3; i++) {
            group += 2;
            for (int j = 0; j < 2; j++) {
                final int finalI = i;
                final int finalJ = j;
                completionService.submit(() -> {
                    String s = finalI + "~" + finalJ;
                    System.out.println(s);
                    return s;
                });
            }
        }

        List<String> results = new ArrayList<>();
        for (int i = 0; i < group; i++) {
            Future<String> take = completionService.take();
            results.add(take.get());
        }

        System.out.println(results.size());
        System.out.println(results);
    }
}
