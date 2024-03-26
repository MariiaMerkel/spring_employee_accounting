package ru.merkel.springemployeeaccounting.models;

import java.util.Objects;
import java.text.NumberFormat;

public class Employee {
    private final String firstName;
    private final String lastName;
    private int department;
    private int salary;
    private final NumberFormat numberFormat = NumberFormat.getCurrencyInstance();

    public Employee(String firstName, String lastName, int department, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.salary = salary;
    }

    public String getFullName() {
        return firstName + ' ' + lastName;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }


    public String toString() {
        return String.format("ФИО сотрудника: %s, отдел: %d, зарплата: %s.", this.getFullName(), department, numberFormat.format(salary));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getFullName(), employee.getFullName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, department, salary, numberFormat);
    }
}
