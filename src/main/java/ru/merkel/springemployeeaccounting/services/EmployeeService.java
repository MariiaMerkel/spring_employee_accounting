package ru.merkel.springemployeeaccounting.services;

import ru.merkel.springemployeeaccounting.models.Employee;

import java.util.Collection;
import java.util.Map;

public interface EmployeeService {
    String add(String firstName, String lastName);
    String remove(String firstName, String lastName);
    String find(String firstName, String lastName);
    Collection<Employee> findAll();
}
