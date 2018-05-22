package test;

import javax.management.MBeanServerConnection;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.Set;

public class JmxTest {

    public static final String JMX_URL_PATTERN = "service:jmx:rmi:///jndi/rmi://%s:%d/jmxrmi";

    public static void main(String[] args) throws Exception {
        String jmxUrl = getServiceUrl("10.202.34.28", 1091);
        JMXServiceURL url = new JMXServiceURL(jmxUrl);
        JMXConnector jmxConnector = JMXConnectorFactory.connect(url, null);
        MBeanServerConnection mbeanConnect = jmxConnector
                .getMBeanServerConnection();
        ObjectName mbeanName = new ObjectName(
                "kafka.log:type=Log,name=*,topic=*,partition=*");
        Set<ObjectInstance> objectInstances = mbeanConnect.queryMBeans(mbeanName, null);
        System.out.println(objectInstances);
    }

    public static String getServiceUrl(String ip, int port) {
        return String.format(JMX_URL_PATTERN, ip, port);
    }

}
