package com.erpak.barter.exceptions;

public class BrandNameAlreadyUsedException extends RuntimeException {

    public BrandNameAlreadyUsedException(String message){
        super(message);
    }
}
