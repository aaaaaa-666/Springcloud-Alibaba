package com.cmj.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.cmj.springcloud.dao")
public class MyBatisConfig {
}
