import com.fasterxml.jackson.databind.JsonNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.*;

/**
 * Created by Administrator on 2015/1/14.
 */
public class QueryProduct {
    public static final int CONNECT_TIMEOUT_MILLIS = 30 * 1000;
    private final static String EMPTY = "";
    private final static String BLANK = " ";
    private final static String SPLIT = ",";

    /**
     * 测试内容
     *
     * @param args
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        SortedMap<String, String> partNames = new TreeMap<>();
        getPartName(partNames);
//        System.out.println(partNames);

        HashMap<String, String> stores = new HashMap<>();
        getStores(stores);
//        System.out.println(stores);

//        printToFile(partNames, stores);
        printToConsole(partNames, stores);
    }

    private static void printToFile(SortedMap<String, String> partNames, HashMap<String, String> stores) throws IOException, InterruptedException {
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\out.csv");
        FileWriter writer = new FileWriter("d:\\out.csv");
        StringBuilder sb = new StringBuilder();
        sb.append(BLANK).append(SPLIT);
        for (String store : stores.keySet()) {
            sb.append(store).append(SPLIT);
            for (int i = 0; i < partNames.size() - 1; i++) {
                sb.append(BLANK).append(SPLIT);
            }
        }
//        System.out.println("sb = " + sb);
        writeAndFlush(writer, sb);

        sb = new StringBuilder();
        sb.append(BLANK).append(SPLIT);
        for (int i = 0; i < stores.size(); i++) {
            for (Map.Entry<String, String> entry : partNames.entrySet()) {
                sb.append(entry.getValue().replace("iPhone 6s", EMPTY).
                        replace("Plus", EMPTY).replace("GB", EMPTY).replace(BLANK, EMPTY))
                        .append(SPLIT);
            }
        }
//        System.out.println("sb = " + sb);
        writeAndFlush(writer, sb);

        while (true) {
            sb = new StringBuilder();
            sb.append(DateFormat.getTimeInstance().format(new Date())).append(SPLIT);
            HashMap<String, Map<String, String>> storeProducts = new HashMap<>();
            getAvailability(storeProducts);
//        System.out.println(storeProducts);

            for (Map.Entry<String, String> entry : stores.entrySet()) {
                Map<String, String> products = storeProducts.get(entry.getValue());
                for (Map.Entry<String, String> entry2 : partNames.entrySet()) {
                    String v = products.get(entry2.getKey());
                    sb.append(v.equals("ALL") ? "1" : "0").append(SPLIT);
                }
            }
            writeAndFlush(writer, sb);
            System.out.println(sb);
            Thread.sleep(10000);
        }
    }

    private static void writeAndFlush(FileWriter writer, StringBuilder sb) throws IOException {
        writer.write(sb.toString());
        writer.write('\n');
        writer.flush();
    }

    private static void printToConsole(SortedMap<String, String> partNames, HashMap<String, String> stores) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("%-50s");
        for (String s : stores.keySet()) {
            sb.append("%-20s");
        }
        String f = sb.toString();

        Object[] params = new Object[stores.keySet().size() + 1];
        params[0] = ' ';
        int i = 1;
        for (String s : stores.keySet()) {
            params[i++] = s;
        }
        System.out.println(String.format(f, params));

        Object[] params2 = new Object[stores.keySet().size() + 1];
        StringBuilder sb2 = new StringBuilder();
        for (int j = 0; j < 130; j++) {
            sb2.append("-");
        }
        String split = sb2.toString();
        HashMap<String, Map<String, String>> storeProducts = new HashMap<>();
        getAvailability(storeProducts);
        int c = 1;
        for (String s : partNames.keySet()) {
            params2[0] = String.format("%s[%s]", s, partNames.get(s)).replace("太空灰", "灰色").replace("玫瑰金色", "玫瑰");
            for (int j = 1; j < params2.length; j++) {
                params2[j] = storeProducts.get(stores.get(params[j])).get(s).replace("NONE", "- -");
            }
            System.out.println(String.format(f, params2));
            if (c++ % 4 == 0) {
                System.out.println(split);
            }
        }
    }

    private static void getPartName(Map<String, String> partNames) throws IOException {
        String url = "https://reserve.cdn-apple.com/HK/zh_HK/reserve/iPhone/availability?channel=1&appleCare=N&iPP=N&partNumber=MKQR2ZP/A&returnURL=http%3A%2F%2Fwww.apple.com%2Fhk-zh%2Fshop%2Fbuy-iphone%2Fiphone6s%2F4.7-%E5%90%8B%E8%9E%A2%E5%B9%95-64gb-%E7%8E%AB%E7%91%B0%E9%87%91%E8%89%B2";
        Document post = Jsoup.connect(url).timeout(CONNECT_TIMEOUT_MILLIS).get();
        for (Element element : post.child(0).child(1).children()) {
            String data = element.data();
            if (data.contains("__productJson__")) {
                JsonNode jsonNode = JsonUtil.readTree(data.substring(data.indexOf("{")));
                for (JsonNode product : jsonNode.get("skus")) {
                    partNames.put(product.get("part_number").asText(), product.get("productDescription").asText());
                }
            }
        }
    }

    private static void getAvailability(Map<String, Map<String, String>> storeProducts) throws IOException {
        String url = "https://reserve.cdn-apple.com/HK/zh_HK/reserve/iPhone/availability.json";
        Document post = Jsoup.connect(url).timeout(CONNECT_TIMEOUT_MILLIS).ignoreContentType(true).get();

        JsonNode jsonNode = JsonUtil.readTree(post.text());
        Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            String key = entry.getKey();
            if (key.startsWith("R4")) {
                JsonNode node = entry.getValue();
                HashMap<String, String> productInfos = new HashMap<>();
                storeProducts.put(key, productInfos);
                fill(productInfos, node);
            }
        }
    }

    private static void fill(HashMap<String, String> productInfos, JsonNode node) {
        Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            String key = entry.getKey();
            if (key.startsWith("time")) {
                continue;
            }
            productInfos.put(key, entry.getValue().asText());
        }
    }

    private static void getStores(Map<String, String> stores) throws IOException {
        String url = "https://reserve.cdn-apple.com/HK/zh_HK/reserve/iPhone/stores.json";
        Document post = Jsoup.connect(url).timeout(CONNECT_TIMEOUT_MILLIS).ignoreContentType(true).get();

        JsonNode jsonNode = JsonUtil.readTree(post.text());
        for (JsonNode store : jsonNode.get("stores")) {
            stores.put(store.get("storeName").asText(), store.get("storeNumber").asText());
        }
    }
}
