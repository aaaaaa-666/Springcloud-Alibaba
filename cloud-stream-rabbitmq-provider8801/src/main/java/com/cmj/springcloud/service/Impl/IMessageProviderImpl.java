package com.cmj.springcloud.service.Impl;

import com.cmj.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;

import java.util.UUID;

@EnableBinding(Source.class) // 定义消息的推送管道
@Slf4j
public class IMessageProviderImpl implements IMessageProvider {

    // 消息发送管道，因为是发送消息出去所以是output
    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        log.info("***serial: " + serial);
        return null;
    }
}
