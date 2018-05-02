package com.lloyds.pharmacy.online.doctor.technical.challenge.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "env")
@Data
public class EnvProperties {
    private String appleUkUrl;
    private boolean captureScreenShot;
    private String screenshotResourcePath;
    private String binaryResourcePath;
    private String chromeDriver;
    private String ieDriver;
}
