package org.apathinternational.faithpathrestful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class FaithPathRestfulApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaithPathRestfulApplication.class, args);
	}
}
