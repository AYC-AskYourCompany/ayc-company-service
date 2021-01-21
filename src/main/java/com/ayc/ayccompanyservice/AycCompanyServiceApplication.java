package com.ayc.ayccompanyservice;

import com.ayc.exceptionhandler.config.EnableAycExceptionHandling;
import com.ayc.keycloaksecurity.config.EnableKeycloakSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableKeycloakSecurity
@EnableAycExceptionHandling
public class AycCompanyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AycCompanyServiceApplication.class, args);
	}

}
