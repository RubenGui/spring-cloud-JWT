package com.emc.springSecurityJWT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCloudJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudJwtApplication.class, args);


//		ApplicationContext context = SpringApplication.run(SpringCloudJwtApplication.class, args);
//
//		UserService userService = context.getBean(UserService.class);
//
//
//		UserEntity usuario = userService.getByUserName("Eusebio").get();
//		System.out.println(usuario.getRoles());
//		System.out.println(userService.existByUserName("kk"));
//		System.out.println(userService.existByUserName("Eusebio"));
	}
}
