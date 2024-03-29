package ru.merkel.springemployeeaccounting.services;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.merkel.springemployeeaccounting.excaptions.*;
import ru.merkel.springemployeeaccounting.models.Employee;

import java.util.*;

@Primary
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private Map<String, Employee> employees = new HashMap<>();
    private static int counter = 5;

    @Override
    @SneakyThrows
    public String add(String firstName, String lastName) {
        if (employees.size() >= counter) {
            throw new EmployeeStorageIsFullException("Список заполнен, добавлять новых сотрудников нельзя");
        }
        Employee e = new Employee(firstName, lastName);
        Employee added = employees.put(e.getFullName(), e);
        if (added != null) {
            throw new EmployeeAlreadyAddedException("Сотрудник с именем " + added.getFullName() + " уже есть в списке");
        }
        return String.format("Добавлен новый сотрудник: %s.", e.getFullName());
    }

    @Override
    public String remove(String firstName, String lastName) {
        Employee e = new Employee(firstName, lastName);
        Employee removed = employees.remove(e.getFullName());
        if (removed == null) {
            throw new EmployeeNotFoundException("Такой сотрудник не найден");
        }
        return String.format("Удалён сотрудник: %s.", removed.getFullName());
    }

    @Override
    public String find(String firstName, String lastName) {
        Employee e = new Employee(firstName, lastName);
        Employee found = employees.get(e.getFullName());
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
