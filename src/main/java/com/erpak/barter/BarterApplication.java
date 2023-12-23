package com.erpak.barter;

import com.erpak.barter.dto.RegisterRequest;
import com.erpak.barter.enums.Role;
import com.erpak.barter.service.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarterApplication.class, args);
	}


/*	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {

			var admin = RegisterRequest.builder()
					.firstName("admintest3")
					.lastName("admintest3")
					.email("admintest12345@gmail.com")
					.birthYear(2000)
					.identityNumber("123456789")
					.password("12345678")
					.role(Role.ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var user = RegisterRequest.builder()
					.firstName("usertest")
					.lastName("usertest")
					.email("usertest123@gmail.com")
					.birthYear(2000)
					.identityNumber("123456789")
					.password("1234567890")
					.role(Role.USER)
					.build();
			System.out.println("User token: " + service.register(user).getAccessToken());


*//*			var manager = RegisterRequest.builder()
					.firstname("managertest12345")
					.lastname("managertest12345")
					.email("managertest12345@gmail.com")
					.password("1234512345")
					.role(Role.MANAGER)
					.build();
			System.out.println("Manager token: " + service.register(manager).getAccessToken());*//*


		};
	}*/

}
