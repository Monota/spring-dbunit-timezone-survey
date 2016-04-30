package com.example.monota.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.monota.spring.service.SampleService;

@SpringBootApplication
@MapperScan("com.example.monota.spring.dao")
public class SpringSampleApplication {

	public static void main(String[] args) {
		try (ConfigurableApplicationContext context = SpringApplication.run(SpringSampleApplication.class, args)) {
			context.getBean(SampleService.class).execute();
		}
	}
}
