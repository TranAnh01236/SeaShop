package org.trananh.shoppingappbackend;

import org.springframework.boot.SpringApplication;	
import org.springframework.boot.autoconfigure.SpringBootApplication;	
import org.springframework.boot.autoconfigure.domain.EntityScan;	
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;	
	
@EntityScan(basePackages = {"org.trananh.shoppingappbackend.model"})	
@EnableJpaRepositories(basePackages = {"org.trananh.shoppingappbackend.repository"})
@SpringBootApplication	
public class ShoppingAppBackendApplication {	
	
	public static void main(String[] args) {
		SpringApplication.run(ShoppingAppBackendApplication.class, args);	
	}
	
}
