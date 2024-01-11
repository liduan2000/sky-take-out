package com.sky.controller.admin;

import com.sky.constant.ShopConstant;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Slf4j
@Api(tags = "店铺相关接口")
public class ShopController {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置营业状态
     *
     * @param status
     * @return
     */
    @PutMapping("/{status}")
    @ApiOperation("设置营业状态")

    public Result setStatus(@PathVariable Integer status) {
        log.info("设置营业状态为：{}", status.equals(ShopConstant.OPEN) ? "营业中" : "打烊中");
        redisTemplate.opsForValue().set(ShopConstant.SHOP_STATUS, status);
        return Result.success();
    }

    /**
     * 获取营业状态
     *
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("获取营业状态")
    public Result<Integer> getStatus() {
        Integer status = (Integer) redisTemplate.opsForValue().get(ShopConstant.SHOP_STATUS);
        if (status != null) {
            log.info("当前营业状态为：{}", status.equals(ShopConstant.OPEN) ? "营业中" : "打烊中");
        }
        return Result.success(status);
    }
}
