package test;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.BoundedExponentialBackoffRetry;

/**
 * Created by 089245 on 2017/5/5.
 */
public class TestOperation {
    public static void main(String[] args) throws Exception {
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder()
                .connectString("10.202.34.28:2182/ocr_event")
                .connectionTimeoutMs(30000)
                .sessionTimeoutMs(30000)
                .retryPolicy(new BoundedExponentialBackoffRetry(1000, 10000, 3));
        CuratorFramework curator = builder.build();
        curator.start();

        try {
            System.out.println(curator.create().forPath("/"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        int count = 5;

//        for (int i = 0; i < count; i++) {
//            String path = "/" + i;
//            curator.create().forPath(path);
//            Thread.sleep(50);
//            curator.delete().forPath(path);
//        }

        for (int i = 0; i < count; i++) {
            String path = "/" + i;
            curator.create().forPath(path);
        }

        for (int i = 0; i < count; i++) {
            String path = "/" + i;
            curator.delete().forPath(path);
        }
    }
}
