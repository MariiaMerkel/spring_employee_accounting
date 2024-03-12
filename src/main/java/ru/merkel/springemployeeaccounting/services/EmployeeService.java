package ru.merkel.springemployeeaccounting.services;

import org.springframework.stereotype.Service;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeAlreadyAddedException;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeNotFoundException;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeStorageIsFullException;

import java.util.List;

@Service
public class EmployeeService {
    private List<Employee> employees;
    private static int counter = 5;

    public EmployeeService(List<Employee> employees) {
        this.employees = employees;
    }

    public Employee add(String firstName, String lastName) {
        if (employees.size() >= counter) {
            throw new EmployeeStorageIsFullException("ArrayIsFull");
        }
        if (check(firstName, lastName)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть в списке");
        }
        Employee e = new Employee(firstName, lastName);
        employees.add(e);
        return e;
    }

    public Employee remove(String firstName, String lastName) {
        Employee e = new Employee(firstName, lastName);
        if(employees.remove(e)){
            return e;
        } else {
            throw new EmployeeNotFoundException("Такой сотрудник не найден");
        }
    }

    public Employee find(String firstName, String lastName) {
        int index = findIndex(firstName, lastName);
        if (index != -1) {
            return employees.get(index);
        } else {
            throw new EmployeeNotFoundException("Такой сотрудник не найден");
        }
    }

    public int findIndex(String firstName, String lastName) {
        int index = -1;
        for (int i = 0; i <= employees.size(); i++) {
            try {
                Employee e = employees.get(i);
                if (e.getFullName().equals(firstName + ' ' + lastName)) {
                    index = i;
                }
            } catch (Exception ex) {
                ex.getStackTrace();
            }
        }
        return index;
    }

    public boolean check(String firstName, String lastName) {
        for (int i = 0; i <= employees.size(); i++) {
            try {
                Employee e = employees.get(i);
                if (e.getFullName().equals(firstName + ' ' + lastName)) {
                    return true;
                }
            } catch (Exception ex) {
                ex.getStackTrace();
            }
        }
        return false;
    }

    public List<Employee> findAll() {
        return employees;
    }
}