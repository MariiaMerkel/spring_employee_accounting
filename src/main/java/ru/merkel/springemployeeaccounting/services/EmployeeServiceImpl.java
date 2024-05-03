package ru.merkel.springemployeeaccounting.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.merkel.springemployeeaccounting.excaptions.*;
import ru.merkel.springemployeeaccounting.models.Employee;

import static org.apache.commons.lang3.StringUtils.*;

import java.util.*;

@Primary
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private Map<String, Employee> employees = new HashMap<>();
    private static int counter = 5;

    @Override
    public Employee add(String firstName, String lastName, Integer salary, Integer department) {
        if (employees.size() >= counter) {
            throw new EmployeeStorageIsFullException("Список заполнен, добавлять новых сотрудников нельзя");
        }
        validateName(firstName, lastName);
        Employee employee = new Employee(firstName.trim(), lastName.trim(), salary, department);
        Employee added = employees.put(employee.getFullName(), employee);
        if (added != null) {
            throw new EmployeeAlreadyAddedException("Сотрудник с именем " + added.getFullName() + " уже есть в списке");
        }
        log.info("Добавлен новый сотрудник: {}.", employee.getFullName());
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        String key = validateName(firstName, lastName);
        Employee removed = employees.remove(key);
        if (removed == null) {
            throw new EmployeeNotFoundException("Такой сотрудник не найден");
        }
        return removed;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        String key = validateName(firstName, lastName);
        Employee found = employees.get(key);
        if (found == null) {
            throw new EmployeeNotFoundException("Такой сотрудник не найден");
        }
        return found;
    }

    private String validateName(String firstName, String lastName) {
        firstName = firstName.trim();
        lastName = lastName.trim();
        if (!isAlpha(firstName) || !isAlpha(lastName)) {
            throw new EmployeeInvalidateException("Некорректное имя или фамилия");
        }
        return capitalize(firstName.toLowerCase()) + ' ' + capitalize(lastName.toLowerCase());
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }
}
