package ru.merkel.springemployeeaccounting.services;

import ru.merkel.springemployeeaccounting.models.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface EmployeeService {
    String add(String firstName, String lastName, Integer salary, Integer department);
    String remove(String firstName, String lastName, Integer salary, Integer department);
    String find(String firstName, String lastName, Integer salary, Integer department);
    String findByMaxSalaryOfDepartment(Integer department);
    String findByMinSalaryOfDepartment(Integer department);
    Set<Employee> findByDepartment(Integer department);
    Collection<List<Employee>> findAll(Integer department);
}
