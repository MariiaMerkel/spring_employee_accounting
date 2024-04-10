package ru.merkel.springemployeeaccounting.services;

import lombok.SneakyThrows;
import ru.merkel.springemployeeaccounting.models.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface EmployeeService {
    String add(String firstName, String lastName, Integer salary, Integer department);
    String remove(String firstName, String lastName);
    String find(String firstName, String lastName);
    String validateName(String firstName, String lastName);
    Collection<Employee> findAll();
}
