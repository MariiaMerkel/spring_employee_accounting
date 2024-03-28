package ru.merkel.springemployeeaccounting.models;

import lombok.Data;
import java.text.NumberFormat;

@Data
public class Employee {
    private final String firstName;
    private final String lastName;
    private Integer salary;
    private Integer department;
    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getCurrencyInstance();

    public Employee(String firstName, String lastName, Integer salary, Integer department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.department = department;
    }

    public String getFullName() {
        return firstName + ' ' + lastName;
    }

    public String getSalary() {
        return NUMBER_FORMAT.format(salary);
    }

    public String toString() {
        return String.format("ФИО сотрудника: %s, отдел: %d, зарплата: %s.", this.getFullName(), department, getSalary());
    }
}
