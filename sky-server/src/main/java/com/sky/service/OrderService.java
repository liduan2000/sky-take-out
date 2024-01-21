package com.sky.service;

import com.sky.dto.OrderPaymentDTO;
import com.sky.dto.OrderSubmitDTO;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderSubmitVO;

public interface OrderService {
    OrderSubmitVO submitOrder(OrderSubmitDTO orderSubmitDTO);

    /**
     * 订单支付
     *
     * @param orderPaymentDTO
     * @return
     */
    OrderPaymentVO payment(OrderPaymentDTO orderPaymentDTO) throws Exception;

    /**
     * 支付成功，修改订单状态
     *
     * @param outTradeNo
     */
    void paySuccess(String outTradeNo);
}
