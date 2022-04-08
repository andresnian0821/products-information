package com.between.products.information.core.config;

import com.between.products.information.core.service.ProductsService;
import com.between.products.information.core.service.impl.ProductsServiceImpl;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:test.properties", ignoreResourceNotFound = true)
public class BaseConfig {

    @Bean
    @Qualifier("productsServiceImpl")
    public ProductsService productsServiceImpl() {
        return Mockito.mock(ProductsServiceImpl.class);
    }

}
