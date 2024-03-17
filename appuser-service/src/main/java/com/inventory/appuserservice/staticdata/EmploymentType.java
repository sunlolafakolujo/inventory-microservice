package com.inventory.appuserservice.staticdata;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmploymentType {
    REGULAR("Regular"),
    CONTRACT("Contract"),
    INTERN("Intern");

    private final String employmentType;
}
