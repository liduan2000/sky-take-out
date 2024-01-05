package com.sky.config;

import com.sky.properties.LocalOssProperties;
import com.sky.utils.LocalOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class OssConfuguration {
    @Bean
    public LocalOssUtil localOssUtil(LocalOssProperties localOssProperties) {
        log.info("开始创建本地文件上传工具类对象: {}", localOssProperties);
        return new LocalOssUtil(localOssProperties.getDirPath());
    }
}
