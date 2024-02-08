package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.OrderPageQueryDTO;
import com.sky.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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

    /**
     * 根据订单状态查询早于某时间的订单
     *
     * @param status
     * @param time
     * @return
     */
    @Select("select * from `order` where status = #{status} and order_time < #{time}")
    List<Order> getByStatusAndOrderTimeLT(Integer status, LocalDateTime time);

    /**
     * 分页条件查询并按下单时间排序
     *
     * @param ordersPageQueryDTO
     */
    Page<Order> queryPage(OrderPageQueryDTO ordersPageQueryDTO);

    /**
     * 根据id查询订单
     *
     * @param id
     */
    @Select("select * from `order` where id=#{id}")
    Order getById(Long id);

    /**
     * 根据状态统计订单数量
     *
     * @param status
     */
    @Select("select count(id) from `order` where status = #{status}")
    Integer countStatus(Integer status);

    /**
     * 获取从begin到end每日总营业额, [begin, end)
     *
     * @param begin
     * @param end
     * @return
     */
    List<Map<String, Object>> getDailySumByRange(LocalDate begin, LocalDate end);
}
