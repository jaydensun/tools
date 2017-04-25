package test;

import org.xerial.snappy.SnappyOutputStream;
import util.JsonUtil;

import java.io.ByteArrayOutputStream;
import java.util.Random;

/**
 * Created by Administrator on 2015/5/11 15:59.
 */
public class ObjectSnappyTest {

    private static Random random = new Random();

    public static void main(String[] args) throws Exception {
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream(100 * 1024 * 1024);
        SnappyOutputStream stream = new SnappyOutputStream(arrayOutputStream);
        int totalLen = 0;
        for (int i = 0; i < 10; i++) {
            Waybill waybill = new Waybill(randomString(), randomString(), randomString(), randomString(), randomString());
            String json = JsonUtil.writeValueAsString(waybill);
            byte[] buffer = json.getBytes();
            System.out.println(String.format("length: %d, content: %s", buffer.length, json));
            totalLen += buffer.length;
            stream.write(buffer);
        }
        stream.flush();

        System.out.println("original: " + totalLen);
        System.out.println("snappy  : " + arrayOutputStream.size());
    }

    private static String randomString() {
        return String.valueOf(random.nextInt());
    }

    static class Waybill {
        private String asdfjkl;
        private String qwertyui;
        private String zoneCode;
        private String address;
        private String waybillNo;

        public Waybill(String asdfjkl, String qwertyui, String zoneCode, String address, String waybillNo) {
            this.asdfjkl = asdfjkl;
            this.qwertyui = qwertyui;
            this.zoneCode = zoneCode;
            this.address = address;
            this.waybillNo = waybillNo;
        }

        public String getAsdfjkl() {
            return asdfjkl;
        }

        public void setAsdfjkl(String asdfjkl) {
            this.asdfjkl = asdfjkl;
        }

        public String getQwertyui() {
            return qwertyui;
        }

        public void setQwertyui(String qwertyui) {
            this.qwertyui = qwertyui;
        }

        public String getZoneCode() {
            return zoneCode;
        }

        public void setZoneCode(String zoneCode) {
            this.zoneCode = zoneCode;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getWaybillNo() {
            return waybillNo;
        }

        public void setWaybillNo(String waybillNo) {
            this.waybillNo = waybillNo;
        }
    }

}
