package com.sky.mapper;

import com.sky.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    /**
     * 插入一条数据
     *
     * @param order
     */
    void insert(Order order);
}
