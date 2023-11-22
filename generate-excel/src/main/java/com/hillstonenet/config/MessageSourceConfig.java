package com.hillstonenet.config;

import com.hillstonenet.service.A;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @Author: Fang Jinxu
 * @Description:
 * @Date: 2023-11-22 15:14
 */
@Configuration
public class MessageSourceConfig {

    @Bean("messageSource")
    public MessageSource getMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public A testA() {
        return new A();
    }

    @Bean
    public A testA1() {
        return new A();
    }
}