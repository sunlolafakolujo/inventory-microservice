package com.inventory.appuserservice.employee.repository;

import com.inventory.appuserservice.appuser.entity.AppUser;
import com.inventory.appuserservice.employee.entity.Employee;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepositoryCustom {
    @Query("FROM Employee e WHERE e.employeeId=?1")
    Employee findByEmployeeId(String employeeId);

    @Query("FROM Employee e WHERE e.firstName=?1 OR e.lastName=?2")
    List<Employee> findByFirstNameOrLastName(String firstName, String lastName);

    @Query("FROM Employee e WHERE e.appUser=?1")
    Employee findByAppUser(AppUser appUser);

    @Query("FROM Employee e WHERE e.dateOfEmployment=?1")
    List<Employee> findByDateOfEmployment(LocalDate dateOfEmployment);
}
