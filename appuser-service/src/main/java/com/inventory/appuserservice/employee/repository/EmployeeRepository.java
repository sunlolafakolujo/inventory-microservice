package com.inventory.appuserservice.employee.repository;

import com.inventory.appuserservice.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeRepositoryCustom {
}
