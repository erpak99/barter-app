package com.erpak.barter.dto;

import com.erpak.barter.enums.BarterStatus;
import com.erpak.barter.model.Barter;
import com.erpak.barter.model.Product;
import com.erpak.barter.model.User;
import lombok.Data;

@Data
public class BarterDTO {

    private UserBarterDTO userOne;
    private ProductBarterDTO productOne;
    private UserBarterDTO userTwo;
    private ProductBarterDTO productTwo;
    private BarterStatus barterStatus;

    public BarterDTO(Barter barter) {
        this.userOne = convertUserToDTO(barter.getUserOne());
        this.productOne = convertProductToDTO(barter.getProductOne());
        this.userTwo = convertUserToDTO(barter.getUserTwo());
        this.productTwo = convertProductToDTO(barter.getProductTwo());
        this.barterStatus = barter.getBarterStatus();
    }

    private UserBarterDTO convertUserToDTO(User user) {
        return new UserBarterDTO(user);
    }

    private ProductBarterDTO convertProductToDTO(Product product) {
        return new ProductBarterDTO(product);
    }

}
