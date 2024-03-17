package com.inventory.appuserservice.makerchecker.exception;

public class MakerCheckerNotFoundException extends RuntimeException {
    public MakerCheckerNotFoundException() {
        super();
    }

    public MakerCheckerNotFoundException(String message) {
        super(message);
    }
}
