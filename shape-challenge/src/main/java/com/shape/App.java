package com.shape;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 *
 */
@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class App {
	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Autowired
	void configureObjectMapper(final ObjectMapper objectMapper) {
		objectMapper.findAndRegisterModules();
	}
	
}
