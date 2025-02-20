package com.reliaquest.api.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name= "employee_name", nullable = false)
    private String employee_name;
    private long employee_salary;
    private int employee_age;
    private String employee_title;
    private String employee_email;


    public Employee(UUID empId, String employee_name, long employee_salary, int employee_age, String employee_title, String employee_email) {

        this.id = UUID.fromString(UUID.randomUUID().toString());
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;
        this.employee_title = employee_title;
        this.employee_email = employee_email;
    }

    public Employee() {

    }

    public UUID getEmpId(){ return id; }

    public void setId(UUID id) {this.id = id;}

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public long getEmployee_salary() {
        return employee_salary;
    }

    public void setEmployee_salary(long employee_salary) {
        this.employee_salary = employee_salary;
    }

    public int getEmployee_age() {
        return employee_age;
    }

    public void setEmployee_age(int employee_age) {
        this.employee_age = employee_age;
    }

    public String getEmployee_title() {
        return employee_title;
    }

    public void setEmployee_title(String employee_title) {
        this.employee_title = employee_title;
    }

    public String getEmployee_email() { return employee_email; }

    public void setEmployee_email(String employee_email) {
        this.employee_email = employee_email;
    }


}
