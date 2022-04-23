package com.cmj.springcloud.service;

import com.cmj.springcloud.entities.CommonResult;
import com.cmj.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider", fallback = PaymentFallbackService.class)
public interface PaymentService {

    /*
    注意：这里并没有用 Hystrix进行降级，而是采用Sentinel，在yml文件开启
     */

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
