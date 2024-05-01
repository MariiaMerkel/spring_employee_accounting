package ru.merkel.springemployeeaccounting.services;

import ru.merkel.springemployeeaccounting.models.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DepartmentService {
    String findByMaxSalaryOfDepartment(Integer department);

    String findByMinSalaryOfDepartment(Integer department);

    Collection<Employee> findByDepartment(Integer department);

    Map<Integer, List<Employee>> findAll();

    String  sumSalaryOfDepartment(Integer id);
}
