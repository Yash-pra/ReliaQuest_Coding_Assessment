package com.reliaquest.api.service;


import com.reliaquest.api.entity.Employee;
import com.reliaquest.api.entity.Input;
import com.reliaquest.api.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }


    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeRepo.findAll();

        if(employees.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No employees found in the system.");
        }
        return employees;
    }


    public List<Employee> findByNameContaining(String searchString) {
       List<Employee> employees = employeeRepo.findByNameContaining(searchString);
       if(employees.isEmpty()) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No employees found in the system with this name.");
       }
      return employees;
    }

    public Employee getEmployeeById(String id) {
        try {
            UUID uuid = UUID.fromString(id); // Convert String to UUID
            return employeeRepo.findById(uuid)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with ID: " + id));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid UUID format: " + id);
        }
    }


    public Employee createEmployee(Input employeeInput) {
        Employee emp = new Employee();

        emp.setEmployee_name(employeeInput.getEmployee_name());
        emp.setEmployee_salary(employeeInput.getEmployee_salary());
        emp.setEmployee_age(employeeInput.getEmployee_age());
        emp.setEmployee_title(employeeInput.getEmployee_title());
        String email= emailGeneration(employeeInput.getEmployee_name());
        System.out.println("Generated Email: " + email);
        emp.setEmployee_email(email);

        Employee savedEmployee = employeeRepo.save(emp);
        return savedEmployee;
    }

    public String emailGeneration(String name){
        return name.toLowerCase().trim().replaceAll("\\s+", "") + "@company.com";
    }

    public void deleteEmployeeById(String id) {
        UUID uuid = UUID.fromString(id);
        employeeRepo.deleteById(uuid);
    }

    public Integer getHighestSalaryOfEmployees() {
        return employeeRepo.findHighestSalary();
    }

    public List<String> getTopTenHighestEarningEmployeeNames() {
        return employeeRepo.findTop10HighestEarningEmployees(PageRequest.of(0, 10));
    }
}
