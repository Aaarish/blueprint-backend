package com.roya.blueprint_backend;

import com.roya.blueprint_backend.auth.config.RSAKeyConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RSAKeyConfigProperties.class)
public class BlueprintBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlueprintBackendApplication.class, args);
	}

}
