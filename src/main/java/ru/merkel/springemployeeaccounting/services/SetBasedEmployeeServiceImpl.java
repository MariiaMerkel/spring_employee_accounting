package ru.merkel.springemployeeaccounting.services;

import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeAlreadyAddedException;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeNotFoundException;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeStorageIsFullException;
import ru.merkel.springemployeeaccounting.models.Employee;

import java.util.*;

@Service
public class SetBasedEmployeeServiceImpl implements EmployeeService{

    private Set<Employee> employees = new HashSet<>();
    private static int counter = 5;
    @SneakyThrows
    @Override
    public String add(String firstName, String lastName, Integer salary, Integer department) {
        if (employees.size() >= counter) {
            throw new EmployeeStorageIsFullException("Список заполнен, добавлять новых сотрудников нельзя");
        }
        Employee e = new Employee(firstName, lastName, salary, department);
        boolean added = employees.add(e);
        if (!added) {
            throw new EmployeeAlreadyAddedException("Сотрудник с именем " + e.getFullName() + " уже есть в списке");
        }
        return String.format("Добавлен новый сотрудник: %s.", e.getFullName());
    }

    @Override
    public String remove(String firstName, String lastName, Integer salary, Integer department) {
        Employee e = new Employee(firstName, lastName, salary, department);
        boolean removed = employees.remove(e);
        if (!removed) {
            throw new EmployeeNotFoundException("Такой сотрудник не найден");
        }
        return String.format("Удалён сотрудник: %s.", e.getFullName());
    }

    @Override
    public String find(String firstName, String lastName, Integer salary, Integer department) {
        Employee e = new Employee(firstName, lastName, salary, department);
        Optional<Employee> found = employees.stream().filter(employee -> employee.getFullName().equals(e.getFullName())).findFirst();
        if (found.isEmpty()) {
            throw new EmployeeNotFoundException("Такой сотрудник не найден");
        }
        return found.toString();
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees);
    }
}
