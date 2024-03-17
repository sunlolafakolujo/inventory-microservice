package com.inventory.appuserservice.employee.dto;

import com.inventory.appuserservice.image.entity.Picture;
import com.inventory.appuserservice.staticdata.EmploymentType;
import com.inventory.appuserservice.staticdata.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class EmployeeResponse {
    private Long id;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String otherName;
    private LocalDate dateOfBirth;
    private Integer age;
    private Gender gender;
    private String email;
    private String mobile;
    private String fullAddress;
    private String city;
    private String landmark;
    private String state;
    private String country;
    private LocalDate dateOfEmployment;
    private LocalDate retirementDate;
    private String stateOfOrigin;
    private String nationality;
    private EmploymentType employmentType;
    private String nextOfKinName;
    private String nextOfKinEmail;
    private String nextOfKinMobile;
    private Picture picture;
}
