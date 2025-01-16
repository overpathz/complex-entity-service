package com.overpathz.complexentityservice.repository;

import com.overpathz.complexentityservice.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
