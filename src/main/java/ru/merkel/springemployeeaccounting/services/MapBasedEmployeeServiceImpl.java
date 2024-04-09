package ru.merkel.springemployeeaccounting.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.merkel.springemployeeaccounting.excaptions.*;
import ru.merkel.springemployeeaccounting.models.Employee;

import java.util.*;

@Service
public class MapBasedEmployeeServiceImpl implements EmployeeService {
    private Map<String, Employee> employees = new HashMap<>();
    private static int counter = 5;

    @Override
    public String add(String firstName, String lastName, Integer salary, Integer department) {
        if (employees.size() >= counter) {
            throw new EmployeeStorageIsFullException("Список заполнен, добавлять новых сотрудников нельзя");
        }
        if(!StringUtils.isEmpty(firstName) && !StringUtils.isEmpty(lastName) && StringUtils.isAlpha(firstName) && StringUtils.isAlpha(lastName)){
            Employee e = new Employee(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName), salary, department);
            Employee added = employees.put(e.getFullName(), e);
            if (added != null) {
                throw new EmployeeAlreadyAddedException("Сотрудник с именем " + added.getFullName() + " уже есть в списке");
            }
            return String.format("Добавлен новый сотрудник: %s.", e.getFullName());
        } else {
            throw new EmployeeInvalidate("Некорректное Имя или фамилия");
        }

    }

    @Override
    public String remove(String firstName, String lastName) {
        Employee removed = employees.remove(firstName + ' ' + lastName);
        if (removed == null) {
            throw new EmployeeNotFoundException("Такой сотрудник не найден");
        }
        return String.format("Удалён сотрудник: %s.", removed.getFullName());
    }

    @Override
    public String find(String firstName, String lastName) {
        Employee found = employees.get(firstName + ' ' + lastName);
        if (found == null) {
            throw new EmployeeNotFoundException("Такой сотрудник не найден");
        }
        return found.toString();
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }
}
