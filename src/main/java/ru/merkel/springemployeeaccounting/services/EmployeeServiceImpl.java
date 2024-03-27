package ru.merkel.springemployeeaccounting.services;

import io.swagger.v3.oas.models.security.SecurityScheme;
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
    public String add(String firstName, String lastName, Integer salary, Integer department) {
        if (employees.size() >= counter) {
            throw new EmployeeStorageIsFullException("Список заполнен, добавлять новых сотрудников нельзя");
        }
        Employee e = new Employee(firstName, lastName, salary, department);
        Employee added = employees.put(e.getFullName(), e);
        if (added != null) {
            throw new EmployeeAlreadyAddedException("Сотрудник с именем " + added.getFullName() + " уже есть в списке");
        }
        return String.format("Добавлен новый сотрудник: %s.", e.getFullName());
    }

    @Override
    @SneakyThrows
    public String remove(String firstName, String lastName, Integer salary, Integer department) {
        Employee e = new Employee(firstName, lastName, salary, department);
        Employee removed = employees.remove(e.getFullName());
        if (removed == null) {
            throw new EmployeeNotFoundException("Такой сотрудник не найден");
        }
        return String.format("Удалён сотрудник: %s.", removed.getFullName());
    }

    @Override
    @SneakyThrows
    public String find(String firstName, String lastName, Integer salary, Integer department) {
        Employee e = new Employee(firstName, lastName, salary, department);
        Employee found = employees.get(e.getFullName());
        if (found == null) {
            throw new EmployeeNotFoundException("Такой сотрудник не найден");
        }
        return found.toString();
    }

    @Override
    public String findByMaxSalaryOfDepartment(Integer department) {
        return null;
    }

    @Override
    public Set<Employee> findByDepartment(Integer department) {
        return null;
    }

    @Override
    @SneakyThrows
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }
}
