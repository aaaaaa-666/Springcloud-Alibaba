package com.cmj.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
OpenFeign日志，有四种级别，这里选用最全的full
 */

@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLever() {
        return Logger.Level.FULL;
    }
}
