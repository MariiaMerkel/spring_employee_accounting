package ru.merkel.springemployeeaccounting.services;

import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.stereotype.Service;
import ru.merkel.springemployeeaccounting.models.Employee;

import java.util.ArrayList;
import java.util.Map;

public interface EmployeeService {
    Employee add(String firstName, String lastName);
    Employee remove(String firstName, String lastName);
    Employee find(String firstName, String lastName);
    ArrayList<Employee> findAll();
}
