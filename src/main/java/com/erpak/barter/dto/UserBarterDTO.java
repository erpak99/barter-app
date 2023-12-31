package com.erpak.barter.dto;

import com.erpak.barter.model.User;
import lombok.Data;

@Data
public class UserBarterDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String city;

    public UserBarterDTO(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.city = user.getCity();
    }

}
