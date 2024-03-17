package com.inventory.appuserservice.employee.dto;

import com.inventory.appuserservice.appuser.entity.AppUser;
import com.inventory.appuserservice.image.entity.Picture;
import com.inventory.appuserservice.nextofkin.entity.NextOfKin;
import com.inventory.appuserservice.staticdata.EmploymentType;
import com.inventory.appuserservice.staticdata.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class EmployeeRequest {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String otherName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private LocalDate dateOfEmployment;
    private String stateOfOrigin;
    private String nationality;
    private EmploymentType employmentType;
    private NextOfKin nextOfKin;
    private AppUser appUser;
    private Picture picture;
}
