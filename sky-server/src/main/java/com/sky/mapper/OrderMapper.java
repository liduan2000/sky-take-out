package com.sky.mapper;

import com.sky.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderMapper {
    /**
     * 插入一条数据
     *
     * @param order
     */
    void insert(Order order);

    /**
     * 根据订单号查询订单
     *
     * @param orderNumber
     */
    @Select("select * from `order` where number = #{orderNumber}")
    Order getByNumber(String orderNumber);

    /**
     * 修改订单信息
     *
     * @param order
     */
    void update(Order order);
}
