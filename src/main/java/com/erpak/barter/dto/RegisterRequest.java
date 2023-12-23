package com.erpak.barter.dto;

import com.erpak.barter.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstName;

    private String lastName;

    private Integer birthYear;

    private String identityNumber;

    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Password can not be empty")
    @Size(min = 8, message = "Password must contain at least 8 characters.")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()_+=[\\\\]\\{\\\\}\\\\\\\\|;:'\",.<>/?`~]).*$",
            message = "Password must contain at least 1 capital letter and at least 1 symbol.")
    private String password;

    private Role role;

}
