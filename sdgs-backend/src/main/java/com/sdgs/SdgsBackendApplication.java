package com.sdgs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sdgs.mapper")
public class SdgsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SdgsBackendApplication.class, args);
	}

}
