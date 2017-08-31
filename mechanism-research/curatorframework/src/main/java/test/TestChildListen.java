package test;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.BoundedExponentialBackoffRetry;

/**
 * Created by 089245 on 2017/5/5.
 */
public class TestChildListen {
    public static void main(String[] args) throws Exception {
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder()
                .connectString("10.202.34.28:2182/ocr_event")
                .connectionTimeoutMs(30000)
                .sessionTimeoutMs(30000)
                .retryPolicy(new BoundedExponentialBackoffRetry(1000, 10000, 3));
        CuratorFramework curator = builder.build();
        curator.start();

        PathChildrenCache cache = new PathChildrenCache(curator, "/", false);
        PathChildrenCacheListener childrenCacheListener = (client, event) -> {
            ChildData data = event.getData();
            switch (event.getType()) {
                case CHILD_ADDED:
                    System.out.println("CHILD_ADDED : " + data.getPath() + "  数据:" + data.getData());
                    break;
                case CHILD_REMOVED:
                    System.out.println("CHILD_REMOVED : " + data.getPath() + "  数据:" + data.getData());
                    break;
                case CHILD_UPDATED:
                    System.out.println("CHILD_UPDATED : " + data.getPath() + "  数据:" + data.getData());
                    break;
                default:
                    break;
            }
        };
        cache.getListenable().addListener(childrenCacheListener);
        cache.start();

        Thread.sleep(100000000L);
    }
}
