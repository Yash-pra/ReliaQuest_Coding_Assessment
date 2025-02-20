package com.reliaquest.api.repository;


import com.reliaquest.api.entity.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, UUID> {

   // List<Employee> findByemployee_nameContainingIgnoreCase(String searchString);

    @Query("SELECT e FROM Employee e WHERE e.employee_name LIKE %:employee_name%")
    List<Employee> findByNameContaining(@Param("employee_name") String employee_name);

    @Query("SELECT MAX(e.employee_salary) FROM Employee e")
    Integer findHighestSalary();

    @Query("SELECT e.employee_name FROM Employee e ORDER BY e.employee_salary DESC")
    List<String> findTop10HighestEarningEmployees(Pageable pageable);

}
