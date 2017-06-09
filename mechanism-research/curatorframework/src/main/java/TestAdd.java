import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.BoundedExponentialBackoffRetry;
import org.apache.zookeeper.KeeperException;

/**
 * Created by 089245 on 2017/5/5.
 */
public class TestAdd {
    public static void main(String[] args) throws Exception {
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder()
                .connectString("10.202.34.31:2181/ocr4")
                .connectionTimeoutMs(30000)
                .sessionTimeoutMs(30000)
                .retryPolicy(new BoundedExponentialBackoffRetry(1000, 10000, 3));
        CuratorFramework curator = builder.build();
        curator.start();

        try {
            System.out.println(curator.getChildren().forPath("/"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            curator.create().creatingParentsIfNeeded().forPath("/");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            curator.create().creatingParentsIfNeeded().forPath("/c/c2");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println(curator.getChildren().forPath("/"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
