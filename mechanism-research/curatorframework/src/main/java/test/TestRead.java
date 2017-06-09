package test;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.BoundedExponentialBackoffRetry;
import org.apache.zookeeper.KeeperException;

/**
 * Created by 089245 on 2017/5/5.
 */
public class TestRead {
    public static void main(String[] args) throws Exception {
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder()
                .connectString("10.202.24.5:2181/kafka/other")
                .connectionTimeoutMs(30000)
                .sessionTimeoutMs(30000)
                .retryPolicy(new BoundedExponentialBackoffRetry(1000, 10000, 3));
        CuratorFramework curator = builder.build();
        curator.start();

//        System.out.println(curator.getChildren().forPath("/consumers"));
        for (String group : curator.getChildren().forPath("/consumers")) {
            try {
                for (String topic : curator.getChildren().forPath("/consumers/" + group + "/owners")) {
                    for (String partition : curator.getChildren().forPath("/consumers/" + group + "/owners/" + topic)) {
                        String x = new String(curator.getData().forPath("/consumers/" + group + "/owners/" + topic + "/" + partition));
                        try {
                            System.out.println(Helper.resolveNodeNameFromThreadId(x, group));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (KeeperException.NoNodeException e) {
            }
        }
    }
}
