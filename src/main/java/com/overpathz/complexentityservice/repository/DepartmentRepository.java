package com.overpathz.complexentityservice.repository;

import com.overpathz.complexentityservice.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query(value = "SELECT COUNT(*) FROM employee e WHERE e.department_id = :deptId", nativeQuery = true)
    int countEmployeesInDepartment(@Param("deptId") Long deptId);

}
