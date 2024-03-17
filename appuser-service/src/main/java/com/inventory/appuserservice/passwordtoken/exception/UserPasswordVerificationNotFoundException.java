package com.inventory.appuserservice.passwordtoken.exception;

public class UserPasswordVerificationNotFoundException extends RuntimeException{
    public UserPasswordVerificationNotFoundException() {
        super();
    }

    public UserPasswordVerificationNotFoundException(String message) {
        super(message);
    }
}
