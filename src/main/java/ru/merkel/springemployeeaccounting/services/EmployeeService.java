package ru.merkel.springemployeeaccounting.services;

import lombok.SneakyThrows;
import ru.merkel.springemployeeaccounting.models.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface EmployeeService {
    Employee add(String firstName, String lastName, Integer salary, Integer department);
    Employee remove(String firstName, String lastName);
    Employee find(String firstName, String lastName);
    Collection<Employee> findAll();
}
