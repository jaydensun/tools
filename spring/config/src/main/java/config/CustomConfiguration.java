package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * Created by Administrator on 2018/3/16.
 */
@Configuration
public class CustomConfiguration {
    @Bean
    public String str() {
        System.out.println("creating str");
        return "123";
    }
}
