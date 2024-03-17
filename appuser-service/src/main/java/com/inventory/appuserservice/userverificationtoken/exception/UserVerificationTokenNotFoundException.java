package com.inventory.appuserservice.userverificationtoken.exception;

public class UserVerificationTokenNotFoundException extends RuntimeException {
    public UserVerificationTokenNotFoundException() {
        super();
    }

    public UserVerificationTokenNotFoundException(String message) {
        super(message);
    }
}
