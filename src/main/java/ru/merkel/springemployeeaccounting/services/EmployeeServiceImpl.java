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
    public String add(String firstName, String lastName, Integer salary, Integer department) {
        if (employees.size() >= counter) {
            throw new EmployeeStorageIsFullException("Список заполнен, добавлять новых сотрудников нельзя");
        }
        String key = validateName(firstName, lastName);
        Employee e = new Employee(firstName, lastName, salary, department);
        Employee added = employees.put(e.getFullName(), e);
        if (added != null) {
            throw new EmployeeAlreadyAddedException("Сотрудник с именем " + added.getFullName() + " уже есть в списке");
        }
        log.info("Добавлен новый сотрудник: {}.", e.getFullName());
        return String.format("Добавлен новый сотрудник: %s.", e.getFullName());
    }

    @Override
    public String remove(String firstName, String lastName) {
        String key = validateName(firstName, lastName);
        Employee removed = employees.remove(key);
        if (removed == null) {
            throw new EmployeeNotFoundException("Такой сотрудник не найден");
        }
        return String.format("Удалён сотрудник: %s.", removed.getFullName());
    }

    @Override
    public String find(String firstName, String lastName) {
        String key = validateName(firstName, lastName);
        Employee found = employees.get(key);
        if (found == null) {
            throw new EmployeeNotFoundException("Такой сотрудник не найден");
        }
        return found.toString();
    }

    @Override
    public String validateName(String firstName, String lastName) {
        if(!isAlpha(firstName) || !isAlpha(lastName)){
            throw new EmployeeInvalidateException("Некорректное имя или фамилия");
        }
        return capitalize(firstName.toLowerCase()) + ' ' + capitalize(lastName.toLowerCase());
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }
}
