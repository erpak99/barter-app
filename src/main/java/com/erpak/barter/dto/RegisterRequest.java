package com.erpak.barter.dto;

import com.erpak.barter.enums.Role;
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

    private String email;

    private String password;

    private Role role;

}
