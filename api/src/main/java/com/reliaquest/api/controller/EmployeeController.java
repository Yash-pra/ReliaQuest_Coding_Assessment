package com.reliaquest.api.controller;

import com.reliaquest.api.entity.Employee;
import com.reliaquest.api.entity.Input;
import com.reliaquest.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController implements IEmployeeController<Employee, Input>{

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> ls = employeeService.getAllEmployees();
        return ResponseEntity.ok(ls);
    }

    //"/search/{searchString}"
    @Override
    public ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString) {
        List<Employee> op = employeeService.findByNameContaining(searchString);
        return ResponseEntity.ok(op);
    }

    //"/{id}"
    @Override
    public ResponseEntity<Employee> getEmployeeById(String id) {
        Employee emp = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(emp);
    }

    //"/highestSalary"
    @Override
    public ResponseEntity<Integer> getHighestSalaryOfEmployees() {

        Integer highestSalary = employeeService.getHighestSalaryOfEmployees();
        return ResponseEntity.ok(highestSalary);
    }

    //"/topTenHighestEarningEmployeeNames"
    @Override
    public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {
        List<String> employeeNames = employeeService.getTopTenHighestEarningEmployeeNames();
        return ResponseEntity.ok(employeeNames);
    }


    @Override
    public ResponseEntity<Employee> createEmployee(Input employeeInput) {
        Employee emp = employeeService.createEmployee(employeeInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(emp);
    }

    //"/{id}"
    @Override
    public ResponseEntity<String> deleteEmployeeById(String id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok("Employee Deleted Successfully");
    }

}
