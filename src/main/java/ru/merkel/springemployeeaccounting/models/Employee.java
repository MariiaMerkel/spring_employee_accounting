package ru.merkel.springemployeeaccounting.models;

import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.text.NumberFormat;

@Data
public class Employee {
    private final String firstName;
    private final String lastName;
    @Getter
    private Integer salary;
    private Integer department;
    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getCurrencyInstance();

    public Employee(String firstName, String lastName, Integer salary, Integer department) {
        this.firstName = StringUtils.capitalize(firstName.toLowerCase());
        this.lastName = StringUtils.capitalize(lastName.toLowerCase());
        this.salary = salary;
        this.department = department;
    }

    public String getFullName() {
        return firstName + ' ' + lastName;
    }

    public String getSalaryStringFormat() {
        return NUMBER_FORMAT.format(salary);
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public String toString() {
        return String.format("ФИО сотрудника: %s, отдел: %d, зарплата: %s.", this.getFullName(), department, getSalaryStringFormat());
    }
}
