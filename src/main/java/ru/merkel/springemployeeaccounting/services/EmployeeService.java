package ru.merkel.springemployeeaccounting.services;

import ru.merkel.springemployeeaccounting.models.Employee;

import java.util.ArrayList;
import java.util.Map;

public interface EmployeeService {
    String add(String firstName, String lastName);
    String remove(String firstName, String lastName);
    String find(String firstName, String lastName);
    Map<String, Employee> findAll();
}
