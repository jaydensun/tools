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

    public void processForm() {
        String url = "http://10.202.40.88:8080/mom-mon/monitorint/queryAllTopic.pub";
//        String url = "http://10.202.34.30:8292/mom-mon/monitorint/queryAllTopic.pub";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<>();
        postParameters.add("clusterName", "vishnusit");

        HttpEntity<MultiValueMap<String, String>> requestEntity  = new HttpEntity<>(postParameters, headers);

        String response = restTemplate.postForObject(url, requestEntity, String.class);
        System.out.println(response);
    }

    public void processJson() {
        String json = "{\"clusterName\":\"vishnusit\"}";

        String url = "http://10.202.40.88:8080/mom-mon/monitorint/queryAllTopic.pub";
//        String url = "http://10.202.34.30:8292/mom-mon/monitorint/queryAllTopic.pub";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=utf-8");

        HttpEntity<String> requestEntity  = new HttpEntity<>(json, headers);

        String response = restTemplate.postForObject(url, requestEntity, String.class);
        System.out.println(response);
    }

    public void createJson() {
        String json = "{\"clusterName\":\"vishnusit\",\"topicName\":\"TEST8881\",\"partitionNum\":\"1\",\"replicationFactor\":\"1\",\"logRetentionHours\":\"12\",\"token\":\"abcd1234\"}";

        String url = "http://10.202.40.88:8080/mom-mon/monitorint/createTopic.pub";
//        String url = "http://10.202.34.30:8292/mom-mon/monitorint/queryAllTopic.pub";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=utf-8");

        HttpEntity<String> requestEntity  = new HttpEntity<>(json, headers);

        String response = restTemplate.postForObject(url, requestEntity, String.class);
        System.out.println(response);
    }

    public void createForm() {
        String url = "http://10.202.40.88:8080/mom-mon/monitorint/createTopic.pub";
//        String url = "http://10.202.34.30:8292/mom-mon/monitorint/queryAllTopic.pub";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<>();
        postParameters.add("clusterName", "vishnusit");
        postParameters.add("topicName", "TEST9992");
        postParameters.add("partitionNum", "1");
        postParameters.add("replicationFactor", "1");
        postParameters.add("logRetentionHours", "12");
        postParameters.add("token", "abcd1234");

        HttpEntity<MultiValueMap<String, String>> requestEntity  = new HttpEntity<>(postParameters, headers);

        String response = restTemplate.postForObject(url, requestEntity, String.class);
        System.out.println(response);
    }
}
