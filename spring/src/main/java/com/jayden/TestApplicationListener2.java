package com.jayden;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;

/**
 * Created by Administrator on 2016/9/29.
 */
public class TestApplicationListener2 implements ApplicationListener<ContextRefreshedEvent>, Ordered {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("fire ContextRefreshedEvent2");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
