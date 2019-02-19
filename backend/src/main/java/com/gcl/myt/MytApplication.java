package com.gcl.myt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.gcl.myt.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class MytApplication {

	public static void main(String[] args) {
		SpringApplication.run(MytApplication.class, args);
	}

}

