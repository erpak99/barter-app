package com.erpak.barter.exceptions;

public class EmailAlreadyUsedException extends RuntimeException {

    public EmailAlreadyUsedException(String message) {

        super(message);
    }

}
