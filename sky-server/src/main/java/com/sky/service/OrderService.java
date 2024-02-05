package com.sky.service;

import com.sky.dto.*;
import com.sky.result.PageResult;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

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

    /**
     * 用户端订单分页查询
     *
     * @param page
     * @param pageSize
     * @param status
     * @return
     */
    PageResult queryPage4User(int page, int pageSize, Integer status);

    /**
     * 查询订单详情
     *
     * @param id
     * @return
     */
    OrderVO details(Long id);

    /**
     * 用户取消订单
     *
     * @param id
     */
    void userCancelById(Long id) throws Exception;

    /**
     * 再来一单
     *
     * @param id
     */
    void repetition(Long id);

    /**
     * 条件搜索订单
     *
     * @param orderPageQueryDTO
     * @return
     */
    PageResult conditionSearch(OrderPageQueryDTO orderPageQueryDTO);

    /**
     * 各个状态的订单数量统计
     *
     * @return
     */
    OrderStatisticsVO statistics();

    /**
     * 接单
     *
     * @param orderConfirmDTO
     */
    void confirm(OrderConfirmDTO orderConfirmDTO);

    /**
     * 拒单
     *
     * @param orderRejectionDTO
     */
    void rejection(OrderRejectionDTO orderRejectionDTO) throws Exception;

    /**
     * 商家取消订单
     *
     * @param orderCancelDTO
     */
    void cancel(OrderCancelDTO orderCancelDTO) throws Exception;

    /**
     * 派送订单
     *
     * @param id
     */
    void delivery(Long id);

    /**
     * 完成订单
     *
     * @param id
     */
    void complete(Long id);

    /**
     * 催单
     *
     * @param id
     */
    void reminder(Long id);
}
