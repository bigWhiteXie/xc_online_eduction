package com.xuecheng.content.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class QiNiuProperties {
    @Value("${file.service.url}")
    public static String url;
    @Value("${file.service.bucket}")
    public static String bucket;
    @Value("${file.service.upload.region}")
    public static String region;
    @Value("${cdn.domain}")
    public static String domain;
    @Value("${file.token.type}")
    public static String tokenType;
    @Value("${file.token.deadline}")
    public static int deadline;
}
