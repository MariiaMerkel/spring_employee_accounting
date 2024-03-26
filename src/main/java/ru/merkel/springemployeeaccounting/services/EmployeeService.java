package ru.merkel.springemployeeaccounting.services;

import ru.merkel.springemployeeaccounting.models.Employee;

import java.util.Collection;
import java.util.Map;

public interface EmployeeService {
    String add(String firstName, String lastName, Integer salary, Integer department);
    String remove(String firstName, String lastName, Integer salary, Integer department);
    String find(String firstName, String lastName, Integer salary, Integer department);
    Collection<Employee> findAll();
}
