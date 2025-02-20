package com.reliaquest.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;


public class Input {

    @NotBlank(message = "Employee name cannot be blank")
    @JsonProperty("employee_name")
    private String employee_name;

    @Positive(message = "Salary must be greater than zero")
    @JsonProperty("employee_salary")
    private long employee_salary;

    @Min(value = 16, message = "Age must be at least 16")
    @Max(value = 75, message = "Age cannot be more than 75")
    @JsonProperty("employee_age")
    private int employee_age;

    @NotBlank(message = "Employee title cannot be blank")
    @JsonProperty("employee_title")
    private String employee_title;

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
}
