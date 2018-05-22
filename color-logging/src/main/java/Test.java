import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.ansi.AnsiOutput;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 089245 on 2017/9/4.
 */
public class Test {
    static Log log = LogFactory.getLog(Test.class);

    public static void main(String[] args) {
        AnsiOutput.setEnabled(AnsiOutput.Enabled.ALWAYS);
        log.info("test msg");

        print("10.202.24.5:2181,10.202.24.6:2181,10.202.24.7:2181");
        print("10.202.24.5:2181,10.202.24.6:2181,10.202.24.7:2181/kafka/st");
        print("10.202.24.5,10.202.24.6,10.202.24.7");
        print("10.202.24.5:2181,10.202.24.6:2181,10.202.24.7:2181/kafka/st");
        print("10.202.24.5:2181,10.202.24.6:2181,10.202.24.7:2182/kafka/st");
    }

    private static void print(String input) {
        List<String> servers = new ArrayList<>();
        Set<String> ports = new HashSet<>();
        for (String node : input.replaceAll("/.*", "").split(",")) {
            String[] split = node.split(":");
            servers.add(split[0]);
            if (split.length > 1) {
                ports.add(split[1]);
            }
        }
        if (ports.size() > 1) {
            throw new RuntimeException("multi port exist : " + ports);
        }
        if (ports.isEmpty()) {
            ports.add("2181");
        }
        System.out.println(servers);
        System.out.println(ports.iterator().next());
    }
}
