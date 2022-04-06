package com.testfinale.Test.Finale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestFinaleApplication {
	private static final Logger logger = LoggerFactory.getLogger(TestFinaleApplication.class);
	@Value("${com.testfinale.testfinaleapplication.saluti}")
	public String salutoinIziale;

	public static void main(String[] args) {

		SpringApplication.run(TestFinaleApplication.class, args);



	}

}
