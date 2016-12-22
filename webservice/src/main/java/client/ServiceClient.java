package client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import test.IService;

import java.util.Date;

/**
 * Created by 089245 on 2016/12/9.
 */
public class ServiceClient {
    public static void main(String[] args) {
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setAddress("http://localhost:8080/hello");
        factoryBean.setServiceClass(IService.class);
        IService service = (IService) factoryBean.create();
        System.out.println(service.hello(new Date().toString()));
    }
}
