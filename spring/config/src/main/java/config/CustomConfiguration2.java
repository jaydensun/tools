package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2018/3/16.
 */
@Configuration
public class CustomConfiguration2 {
    @Bean
    public String str() {
        System.out.println("creating str2");
        return "234";
    }
}
