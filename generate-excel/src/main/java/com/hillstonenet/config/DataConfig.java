package com.hillstonenet.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: Fang Jinxu
 * @Description:
 * @Date: 2023-11-21 17:37
 */
@Component
@PropertySource(value = "classpath:./config/data.properties")
@ConfigurationProperties(prefix = "data")
public class DataConfig {

    private String neName;

    private Integer neId;

    private String sn;

    private Byte online;

    private String version;

}