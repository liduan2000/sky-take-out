package com.sky.service;

import com.sky.entity.Setmeal;

import java.util.List;

public interface SetmealService {

    /**
     * 条件查询
     *
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);
}
