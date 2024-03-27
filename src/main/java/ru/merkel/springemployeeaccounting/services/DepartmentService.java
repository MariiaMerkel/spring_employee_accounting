package ru.merkel.springemployeeaccounting.services;

import org.springframework.stereotype.Service;
import ru.merkel.springemployeeaccounting.models.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface DepartmentService {
    String findByMaxSalaryOfDepartment(Integer department);
    String findByMinSalaryOfDepartment(Integer department);
    Set<Employee> findByDepartment(Integer department);
    Collection<List<Employee>> findAll(Integer department);
}
