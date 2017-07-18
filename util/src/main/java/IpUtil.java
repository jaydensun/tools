import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by 089245 on 2017/7/17.
 */
public class IpUtil {
    public static void main(String[] args) throws SocketException {
        getIp();
    }

    private static void getIp() throws SocketException {
        Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip;
        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
            System.out.println(netInterface.getName());
            Enumeration addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                ip = (InetAddress) addresses.nextElement();
                if (ip != null && ip instanceof Inet4Address
                        && ip.isSiteLocalAddress()
                        && !ip.isLoopbackAddress()) {
                    String hostAddress = ip.getHostAddress();
                    if (hostAddress.startsWith("10.")) {
                        System.out.println(ip.getHostName());
                        System.out.println(ip.isSiteLocalAddress());
                        System.out.println(ip.isLinkLocalAddress());
                        System.out.println(ip.isAnyLocalAddress());
                        System.out.println("本机的IP = " + hostAddress);
                    }
                }
            }
        }
    }
}
