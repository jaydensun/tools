import java.util.*;

/**
 * 描述：
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON          REASON
 *  1    2015-11-04       089245          Create
 * ****************************************************************************
 * </pre>
 *
 * @author 089245
 * @since 1.0
 */
public class NetHogs {
    static String regex = ".*[1-9][0-9]*\\s+.+\\s+[0-9\\.]+\\s+[0-9\\.]+";

    public static Map<String, Map<Integer, Rate>> netLoad() {
        String path = "nethogs -d 3";
        List<String> rs = ShellUtil.runShell(path, 4);
        Map<String, Map<Integer, Rate>> ethProcessRates = new HashMap<>();
        for (String r : rs) {
            if (!r.matches(regex)) {
                continue;
            }
            StringTokenizer tokenizer = new StringTokenizer(r);
            int i = 0;
            while (tokenizer.hasMoreElements()) {
                i++;
                String pidStr = tokenizer.nextToken().trim();
                Integer pid = Integer.parseInt(pidStr.replaceAll("[^0-9]", ""));
                String eth = tokenizer.nextToken().trim();
                double send = Double.parseDouble(tokenizer.nextToken().trim());
                double receive = Double.parseDouble(tokenizer.nextToken().trim());
                Map<Integer, Rate> processRates = ethProcessRates.get(eth);
                if (processRates == null) {
                    processRates = new HashMap<>();
                    ethProcessRates.put(eth, processRates);
                }
                processRates.put(pid, new Rate(send, receive));
            }
        }
        return ethProcessRates;
    }

    public static class Rate {
        double send;
        double receive;

        public Rate(double send, double receive) {
            this.send = send;
            this.receive = receive;
        }

        @Override
        public String toString() {
            return "NetHogs.Rate{" +
                    "send=" + send +
                    ", receive=" + receive +
                    '}';
        }
    }
}
