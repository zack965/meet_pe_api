package com.meetpe_api.meetpe_api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "meetpe API", version = "2.0", description = "Meet pe Information"))
@SecurityScheme(type = SecuritySchemeType.APIKEY,name = "API-KEY",in = SecuritySchemeIn.HEADER)
public class MeetpeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetpeApiApplication.class, args);
	}

}
