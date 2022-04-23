package com.cmj.springcloud.service;

import org.springframework.web.bind.annotation.PathVariable;

public interface PaymentService {

    public String paymentInfoOk(Integer id);

    public String paymentInfoTimeOut(Integer id);

    public String paymentCircuitBreaker(@PathVariable("id") Integer id);
}
