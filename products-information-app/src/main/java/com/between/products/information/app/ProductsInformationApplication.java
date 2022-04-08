package com.between.products.information.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients("com.between.products.information.client.operations")
@ComponentScan(basePackages = {
		"com.between.products.information.app",
		"com.between.products.information.core"
})
public class ProductsInformationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsInformationApplication.class, args);
	}

}
