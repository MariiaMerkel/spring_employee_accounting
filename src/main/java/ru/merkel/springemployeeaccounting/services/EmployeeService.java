package ru.merkel.springemployeeaccounting.services;

import lombok.SneakyThrows;
import ru.merkel.springemployeeaccounting.models.Employee;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface EmployeeService {
    String add(String firstName, String lastName, Integer salary, Integer department);
    String remove(String firstName, String lastName, Integer salary, Integer department);
    String find(String firstName, String lastName, Integer salary, Integer department);

    String findByMaxSalaryOfDepartment(Integer department);

    @SneakyThrows
    Set<Employee> findByDepartment(Integer department);

    Collection<Employee> findAll();
}
