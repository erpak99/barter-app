package com.erpak.barter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {

    private String currentPassword;
    @NotBlank(message = "Password can not be empty")
    @Size(min = 8, message = "Password must contain at least 8 characters.")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()_+=[\\\\]\\{\\\\}\\\\\\\\|;:'\",.<>/?`~]).*$",
            message = "Password must contain at least 1 capital letter and at least 1 symbol.")
    private String newPassword;
    private String confirmationPassword;
}
