package ru.merkel.springemployeeaccounting.services;

import org.springframework.stereotype.Service;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeAlreadyAddedException;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeNotFoundException;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeStorageIsFullException;
import ru.merkel.springemployeeaccounting.models.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private Map<String, Employee> employees = new HashMap<>();
    private static int counter = 5;

    @Override
    public Employee add(String firstName, String lastName) {
        if (employees.size() >= counter) {
            throw new EmployeeStorageIsFullException("ArrayIsFull");
        }
        Employee e = new Employee(firstName, lastName);
        Employee e2 = employees.put(e.getFullName(), e);
        if (e2 != null) {
            throw new EmployeeAlreadyAddedException("Сотрудник с именем " + e2.getFullName() + " уже есть в списке");
        }
        return e;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee e = new Employee(firstName, lastName);
        try {
            return employees.remove(e.getFullName());
        } catch (Exception ex) {
            throw new EmployeeNotFoundException("Такой сотрудник не найден");
        }
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee e = new Employee(firstName, lastName);
        try {
            return employees.get(e);
        } catch (Exception ex) {
            throw new EmployeeNotFoundException("Такой сотрудник не найден");
        }
    }

    @Override
    public ArrayList<Employee> findAll() {
        return new ArrayList<Employee>(employees.values());
    }
}
