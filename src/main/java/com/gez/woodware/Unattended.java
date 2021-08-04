package com.gez.woodware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.Arrays;

@EnableSwagger2Doc
@SpringBootApplication
public class Unattended extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Unattended.class);
	}


	public static void main(String[] args) {


		System.out.println("http://localhost:8080/swagger-ui.html");

		SpringApplication.run(Unattended.class, args);
	}

}
