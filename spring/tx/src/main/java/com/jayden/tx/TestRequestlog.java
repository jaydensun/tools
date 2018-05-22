package com.jayden.tx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 089245 on 2017/7/18.
 */
public class TestRequestLog {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("^(.*?)\\..{3},\\s*(.*?),\\s*(.*?),\\s*(.*)$");
        CharSequence line = "2017-07-10 00:05:26.219,873092, /aibssSpace/findBy.pvt, {\"mainDatas\":\"[{\\\"keyMain\\\":\\\"2017-07-10_515R_515W1430_020X0300_2017-07-11\\\",\\\"sendBelongNetworkCode\\\":\\\"515R\\\",\\\"dataSource\\\":\\\"2\\\",\\\"planSendBatchDt\\\":\\\"2017-07-10\\\",\\\"planSendBatch\\\":\\\"515W1430\\\",\\\"planArriveBatch\\\":\\\"020X0300\\\",\\\"planArriveBatchDt\\\":\\\"2017-07-11\\\",\\\"requriedId\\\":\\\"1050878\\\"},{\\\"keyMain\\\":\\\"2017-07-10_515R_515W0300_010RA1300_2017-07-10\\\",\\\"sendBelongNetworkCode\\\":\\\"515R\\\",\\\"dataSource\\\":\\\"2\\\",\\\"planSendBatchDt\\\":\\\"2017-07-10\\\",\\\"planSendBatch\\\":\\\"515W0300\\\",\\\"planArriveBatch\\\":\\\"010RA1300\\\",\\\"planArriveBatchDt\\\":\\\"2017-07-10\\\",\\\"requriedId\\\":\\\"1050877\\\"}]\",\"limit\":\"20\",\"start\":\"0\",\"page\":\"1\"}";
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            int groupCount = matcher.groupCount();
            for (int i = 0; i < groupCount; i++) {
                System.out.println(matcher.group(i + 1));
            }
        }
    }
}
