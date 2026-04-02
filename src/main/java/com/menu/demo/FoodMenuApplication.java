package com.menu.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class FoodMenuApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodMenuApplication.class, args);
     PasswordEncoder encoder = new BCryptPasswordEncoder();
        
        String adminPassword = encoder.encode("admin123");
        String deliveryPassword = encoder.encode("delivery123");

        System.out.println("ADMIN password: " + adminPassword);
        System.out.println("DELIVERY password: " + deliveryPassword);
	}

}
