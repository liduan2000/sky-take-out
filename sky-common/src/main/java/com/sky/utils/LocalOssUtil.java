package com.sky.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Data
@AllArgsConstructor
@Slf4j
public class LocalOssUtil {
    private String dirPath;

    public String upload(byte[] bytes, String objectName) {
//        StringBuilder stringBuilder = new StringBuilder(dirPath);
//        stringBuilder.append("/").append(objectName);

        Path filePath = Paths.get(dirPath, objectName);
        try {
            Files.write(filePath, bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        log.info("文件上传到: {}", filePath);

        return filePath.toString();
    }
}
