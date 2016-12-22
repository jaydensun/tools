package test;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 * Created by 089245 on 2016/12/9.
 */
public class ServiceServer {
    public static void main(String[] args) {
        String url="http://localhost:8080/hello";
        JaxWsServerFactoryBean factoryBean=new JaxWsServerFactoryBean();
        factoryBean.setAddress(url);
        factoryBean.setServiceClass(IService.class);
        factoryBean.setServiceBean(new ServiceImpl());
        factoryBean.create();
    }
}
