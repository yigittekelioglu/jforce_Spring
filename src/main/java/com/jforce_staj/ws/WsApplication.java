package com.jforce_staj.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.jforce_staj.ws.user.Role;
import com.jforce_staj.ws.user.Role.RoleType;
import com.jforce_staj.ws.user.RoleRepository;
import com.jforce_staj.ws.user.UserService;



@SpringBootApplication//(exclude = SecurityAutoConfiguration.class)
public class WsApplication {
	
	
	@Autowired
    RoleRepository roleRepository;
	
	@Autowired
    UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(WsApplication.class, args);
	}
	
	@Bean
	CommandLineRunner createInıtial() { //program baslayınca hızlı olusturuyo ıstedıgımnızı constructor gibi
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				//buraya istediğimiz işlem
				//roleRepository.save(new Role(RoleType.ADMIN));
	            //roleRepository.save(new Role(RoleType.IK));
	            //roleRepository.save(new Role(RoleType.INVENTORYMASTER));
				userService.assignAdminRoleToAdminUser();
			}
			
		};
	}

}
