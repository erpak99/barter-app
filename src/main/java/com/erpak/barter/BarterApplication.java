package com.erpak.barter;

import com.erpak.barter.exceptions.ProblemDetails;
import com.erpak.barter.exceptions.ValidationProblemDetails;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@SpringBootApplication
@RestControllerAdvice
public class BarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarterApplication.class, args);
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ProblemDetails handleBusinessException(RuntimeException exception) {
		ProblemDetails problemDetails = new ProblemDetails();
		problemDetails.setMessage(exception.getMessage());

		return problemDetails;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ProblemDetails handleValidationException(MethodArgumentNotValidException ex) {
		ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails();
		validationProblemDetails.setMessage("VALIDATION EXCEPTION");
		validationProblemDetails.setValidationErrors(new HashMap<>());

		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			validationProblemDetails.getValidationErrors().put(fieldError.getField(), fieldError.getDefaultMessage());
		}

		return validationProblemDetails;
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
