package com.hospital.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.Async;

@Async
@SpringBootApplication
@EnableCaching
public class AIOMHIS {

	public static void main(String[] args) {
		SpringApplication.run(AIOMHIS.class, args);
	}

}
