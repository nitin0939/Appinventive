package com.example.appinventiv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.example.appinventiv.config","com.example.appinventiv.repository","com.example.appinventiv.model","com.example.appinventiv.service","com.example.appinventiv"})
@EntityScan("com.example.appinventiv.model")
@SpringBootApplication
public class AppinventivApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppinventivApplication.class, args);
	}

}
