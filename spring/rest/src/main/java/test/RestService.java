package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Created by 089245 on 2017/5/9.
 */
@Component
public class RestService {
    private static Logger logger = LoggerFactory.getLogger(RestService.class);
    @Autowired
    private RestTemplate restTemplate;

    public void process() {

        String json = "{\"clusterName\":\"sfdw\"}";

        String url = "http://10.202.34.30:8292/mom-mon/monitorint/queryAllTopic.pub";
//        String url = "http://10.202.34.30:8292/mom-mon/monitorint/queryAllTopic.pub?clusterName=sfdw";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=utf-8");

        MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<>();
        postParameters.add("clusterName", "sfdw");

        HttpEntity<String> requestEntity  = new HttpEntity<>(json, headers);
//        HttpEntity<MultiValueMap<String, String>> requestEntity  = new HttpEntity<>(postParameters, headers);

        String response = restTemplate.postForObject(url, requestEntity, String.class);
        System.out.println(response);
    }
}
