package com.misakicorpspringboot.app.orquestador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SpringbootServicioOrquestadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioOrquestadorApplication.class, args);
	}

}
