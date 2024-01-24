package com.sky.task;

import com.sky.entity.Order;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class OrderTask {
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 处理付款超时的订单，每一分钟触发一次
     */
    @Scheduled(cron = "0 * * * * ?")
    public void processPaymentTimeoutOrder() {
        LocalDateTime time = LocalDateTime.now();
        log.info("定时处理付款超时订单，当前时间：{}", time);
        List<Order> orderList = orderMapper.getByStatusAndOrderTimeLT(Order.PENDING_PAYMENT, time.minusMinutes(15));
        if (orderList != null && !orderList.isEmpty()) {
            for (Order order : orderList) {
                order.setStatus(Order.CANCELLED);
                order.setCancelTime(time);
                order.setCancelReason("付款超时，自动取消");
                orderMapper.update(order);
            }
        }
    }

    /**
     * 处理昨日派送中(未完成)订单，每天凌晨一点触发一次
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void processDeliveryOrder() {
        LocalDateTime time = LocalDateTime.now();
        log.info("定时处理派送中订单，当前时间：{}", time);
        List<Order> orderList = orderMapper.getByStatusAndOrderTimeLT(Order.DELIVERY_IN_PROGRESS, time.minusMinutes(60));
        if (orderList != null && !orderList.isEmpty()) {
            for (Order order : orderList) {
                order.setStatus(Order.COMPLETED);
                order.setDeliveryTime(time);
                orderMapper.update(order);
            }
        }
    }
}
