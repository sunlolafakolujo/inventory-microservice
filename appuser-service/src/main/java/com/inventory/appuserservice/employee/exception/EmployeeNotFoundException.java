package com.inventory.appuserservice.employee.exception;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException() {
        super();
    }

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
