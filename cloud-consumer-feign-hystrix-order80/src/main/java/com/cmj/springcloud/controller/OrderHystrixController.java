package com.cmj.springcloud.controller;

import com.cmj.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    /*在PaymentHystrixService接口中做了降级处理（与业务逻辑代码进行解耦），
    1. 这里的paymentInfoOk()方法并没有添加降级注解，调用出现问题时，在PaymentHystrixService接口中走降级
    2. 这里的paymentInfoTimeOut()方法设置的降级注解，将会走本类中的全局降级方法
    -----------2这种方式存在不妥，降级兜底方法与业务逻辑代码在同一个类中，耦合度高。------------

     */

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String paymentInfoOk(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfoOk(id);
        return result;
    }


    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    @HystrixCommand // 这里没有特别指定降级兜底方法，将使用全局的
    public String paymentInfoTimeOut(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfoTimeOut(id);
        return result;
    }

    // 服务降级用的兜底方法
    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "  paymentInfoTimeOut,id: " + id + "。服务降级兜底(消费者80)";
    }

    // 全局的服务降级用的兜底方法
    public String payment_Global_FallbackMethod() {
        return "Global异常处理信息，全局服务降级处理(消费者80)";
    }
}
