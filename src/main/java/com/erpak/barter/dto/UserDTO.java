package com.erpak.barter.dto;

import com.erpak.barter.enums.Role;
import com.erpak.barter.model.User;
import lombok.Data;

@Data
public class UserDTO {

    private String firstName;
    private String lastName;
    private Integer birthYear;
    private String identityNumber;
    private String email;
    private String password;
    private String city;
    private Role role;

    public UserDTO(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.birthYear = user.getBirthYear();
        this.identityNumber = user.getIdentityNumber();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.city = user.getCity();
        this.role = user.getRole();
    }

}
