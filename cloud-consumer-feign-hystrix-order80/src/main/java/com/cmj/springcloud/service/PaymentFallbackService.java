package com.cmj.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {

    @Override
    public String paymentInfoOk(Integer id) {
        return "---PaymentFallbackService---paymentInfoOk fall back，与业务逻辑代码解耦的服务降级兜底方法（消费者80）";
    }

    @Override
    public String paymentInfoTimeOut(Integer id) {
        return "---PaymentFallbackService---paymentInfoTimeOut fall back，与业务逻辑代码解耦的服务降级兜底方法（消费者80）";
    }
}
