package com.cmj.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
注意这个类不能放在主启动类所在的包及其子包中
 */

@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule() {
        // 负载均衡方式定义为随机
        return new RandomRule();
    }
}
