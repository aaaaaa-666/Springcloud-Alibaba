package com.cmj.springcloud.service.impl;

import com.cmj.springcloud.dao.OrderDao;
import com.cmj.springcloud.domain.Order;
import com.cmj.springcloud.service.AccountService;
import com.cmj.springcloud.service.OrderService;
import com.cmj.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;


    /*
    在account微服务中故意设置了超时异常
     使用seata全局注解，保证分布式事务
     */
    @Override
//    @GlobalTransactional(rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("---开始新建订单---");
        // 1. 创建订单
        orderDao.create(order);

        // 2. 订单微服务调用库存微服务，扣减库存
        log.info("---订单微服务开始调用库存微服务，扣减库存---start");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("---订单微服务开始调用库存微服务，扣减库存---end");

        // 3. 订单微服务调用账户微服务，扣减money
        log.info("---订单微服务开始调用账户微服务，扣减Money---start");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("---订单微服务开始调用账户微服务，扣减Money---end");

        // 4. 修改订单状态
        orderDao.update(order.getUserId(), 0);
        log.info("订单状态修改完成, 订单完成啦！");
    }
}
