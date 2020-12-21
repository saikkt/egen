package com.egen.shipping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class EgenShippingServiceApplication {

	static final String queueName = "shipping-queue";

	public static void main(String[] args) {
		SpringApplication.run(EgenShippingServiceApplication.class, args);
	}

}
