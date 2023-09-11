package com.example.ordercommand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // Feign Client 활성화
public class OrderCommandApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderCommandApplication.class, args);
	}

}
