package com.jforce_staj.ws;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;



@SpringBootApplication//(exclude = SecurityAutoConfiguration.class)
public class WsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsApplication.class, args);
	}
	
	
	
	
	@Bean
	CommandLineRunner createInıtial() { //program baslayınca hızlı olusturuyo ıstedıgımnızı constructor gibi
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				//buraya istediğimiz işlem
				
			}
			
		};
	}

}
