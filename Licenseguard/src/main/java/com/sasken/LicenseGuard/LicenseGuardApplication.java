package com.sasken.LicenseGuard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LicenseGuardApplication {

	public static void main(String[] args) {
		SpringApplication.run(LicenseGuardApplication.class, args);
	}

}
