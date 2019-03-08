package com.mandalart_server;

import com.mandalart_server.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class MandalartServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(MandalartServerApplication.class, args);
	}

}
