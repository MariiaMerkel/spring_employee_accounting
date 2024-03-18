package ru.merkel.springemployeeaccounting.services;

import org.springframework.stereotype.Service;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeAlreadyAddedException;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeNotFoundException;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeStorageIsFullException;
import ru.merkel.springemployeeaccounting.models.Employee;
import java.util.ArrayList;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    ArrayList<Employee> employees = new ArrayList<>();
    private static int counter = 5;

    @Override
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

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee e = new Employee(firstName, lastName);
        if (employees.remove(e)) {
            return e;
        } else {
            throw new EmployeeNotFoundException("Такой сотрудник не найден");
        }
    }

    @Override
    public Employee find(String firstName, String lastName) {
        for (Employee e : employees) {
            if (e.getFullName().equals(firstName + ' ' + lastName)) {
                return e;
            }
        }
        throw new EmployeeNotFoundException("Такой сотрудник не найден");
    }

    @Override
    public ArrayList<Employee> findAll() {
        return employees;
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
}
