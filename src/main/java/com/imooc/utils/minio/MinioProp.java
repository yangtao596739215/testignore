package com.imooc.utils.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author Yang Tao
 * @Date 2020/7/12 9:45
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioProp {
    /**
     * 连接url
     */
    private String endpoint;
    /**
     * 用户名
     */
    private String accesskey;
    /**
     * 密码
     */
    private String secretKey;
}
