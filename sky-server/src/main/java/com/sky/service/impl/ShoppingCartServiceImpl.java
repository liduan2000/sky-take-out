package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetmealMapper setmealMapper;

    /**
     * 添加购物车
     *
     * @param shoppingCartDTO
     */
    public void addShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        // 查询购物车中是否已存在要添加的商品
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        shoppingCart.setUserId(BaseContext.getCurrentId());
        List<ShoppingCart> shoppingCartList = shoppingCartMapper.list(shoppingCart);
        // 如果已存在，只需数量加1
        if (shoppingCartList != null && !shoppingCartList.isEmpty()) {
            // 只会查到一条数据
            ShoppingCart cart = shoppingCartList.get(0);
            cart.setNumber(cart.getNumber() + 1);
            shoppingCartMapper.updateNumberById(cart);
        } else {
            // 如果不存在，插入新数据
            // 判断添加的是菜品还是套餐
            Long dishId = shoppingCartDTO.getDishId();
            Long setmealId = shoppingCartDTO.getSetmealId();

            if (dishId != null) {
                Dish dish = dishMapper.getById(dishId);
                shoppingCart.setName(dish.getName());
                shoppingCart.setImage(dish.getImage());
                shoppingCart.setAmount(dish.getPrice());
            } else {
                Setmeal setmeal = setmealMapper.getById(setmealId);
                shoppingCart.setName(setmeal.getName());
                shoppingCart.setImage(setmeal.getImage());
                shoppingCart.setAmount(setmeal.getPrice());
            }
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());

            shoppingCartMapper.insert(shoppingCart);
        }
    }

    /**
     * 查看购物车
     *
     * @return
     */
    public List<ShoppingCart> showShoppingCart() {
        Long userId = BaseContext.getCurrentId();
        ShoppingCart shoppingCart = ShoppingCart.builder().userId(userId).build();
        return shoppingCartMapper.list(shoppingCart);
    }

    /**
     * 清空购物车
     */
    public void cleanShoppingCart() {
        Long userId = BaseContext.getCurrentId();
        shoppingCartMapper.deleteByUserId(userId);
    }

    /**
     * 删除购物车中一个商品
     *
     * @param shoppingCartDTO
     */
    public void subShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        //设置查询条件，查询当前登录用户的购物车数据
        shoppingCart.setUserId(BaseContext.getCurrentId());

        List<ShoppingCart> list = shoppingCartMapper.list(shoppingCart);

        if (list != null && !list.isEmpty()) {
            shoppingCart = list.get(0);

            Integer number = shoppingCart.getNumber();
            if (number == 1) {
                //当前商品在购物车中的份数为1，直接删除当前记录
                shoppingCartMapper.deleteById(shoppingCart.getId());
            } else {
                //当前商品在购物车中的份数不为1，修改份数即可
                shoppingCart.setNumber(shoppingCart.getNumber() - 1);
                shoppingCartMapper.updateNumberById(shoppingCart);
            }
        }
    }
}
