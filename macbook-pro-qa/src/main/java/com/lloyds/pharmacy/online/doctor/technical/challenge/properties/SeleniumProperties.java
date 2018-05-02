package com.lloyds.pharmacy.online.doctor.technical.challenge.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "selenium")
@Data
public class SeleniumProperties {
    private long implicitWaitTime;
    private int webDriverWaitTimeout;
    private String browser;
    private boolean incognitoMode;
    private String ieRemoteIp;
    private String ieRemotePort;

}
