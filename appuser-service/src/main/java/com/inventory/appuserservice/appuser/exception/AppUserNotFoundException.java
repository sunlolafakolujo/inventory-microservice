package com.inventory.appuserservice.appuser.exception;

public class AppUserNotFoundException extends RuntimeException{
    public AppUserNotFoundException() {
        super();
    }

    public AppUserNotFoundException(String message) {
        super(message);
    }
}
