package com.between.products.information.client.config;

import feign.Logger;
import feign.gson.GsonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Bean
    public Logger.Level feignLoggerLevel()  {
        return Logger.Level.FULL;
    }

    @Bean
    public GsonEncoder encoder(){
        return new GsonEncoder();
    }
}
