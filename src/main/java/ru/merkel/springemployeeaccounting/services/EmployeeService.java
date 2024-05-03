package ru.merkel.springemployeeaccounting.services;

import ru.merkel.springemployeeaccounting.models.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee add(String firstName, String lastName, Integer salary, Integer department);

    Employee remove(String firstName, String lastName);

    Employee find(String firstName, String lastName);

    Collection<Employee> findAll();
}
