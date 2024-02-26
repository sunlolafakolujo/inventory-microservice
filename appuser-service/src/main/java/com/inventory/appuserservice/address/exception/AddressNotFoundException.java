package com.inventory.appuserservice.address.exception;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException() {
        super();
    }

    public AddressNotFoundException(String message) {
        super(message);
    }
}
