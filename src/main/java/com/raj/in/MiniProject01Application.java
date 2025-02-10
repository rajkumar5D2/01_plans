package com.raj.in;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.raj.in.entity.PlanCategoryEntity;

@SpringBootApplication
@ComponentScan(basePackages = {"com.raj.in.service","com.raj.in.rest","com.raj.in.properties","com.raj.in.Constants"})
public class MiniProject01Application {

	public static void main(String[] args) {
		SpringApplication.run(MiniProject01Application.class, args);
	}

}
