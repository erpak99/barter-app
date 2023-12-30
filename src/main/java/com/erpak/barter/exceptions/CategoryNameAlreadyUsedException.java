package com.erpak.barter.exceptions;

public class CategoryNameAlreadyUsedException extends RuntimeException {
    public CategoryNameAlreadyUsedException(String message) {
        super(message);
    }
}
