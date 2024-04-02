package ru.merkel.springemployeeaccounting.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeAlreadyAddedException;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeNotFoundException;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeStorageIsFullException;
import ru.merkel.springemployeeaccounting.models.Employee;

import java.util.*;

@Primary
@Service
public class SetBasedEmployeeServiceImpl implements EmployeeService {

    private final Set<Employee> employees = new HashSet<>();
    private static final int COUNTER = 5;

    @Override
    public String add(String firstName, String lastName, Integer salary, Integer department) {
        if (employees.size() >= COUNTER) {
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
    public String remove(String firstName, String lastName) {
        Iterator<Employee> i = employees.iterator();
        while (i.hasNext()) {
            if (i.next().getFullName().equals(firstName + ' ' + lastName)) {
                i.remove();
                return String.format("Удалён сотрудник: %s.", firstName + ' ' + lastName);
            }
        }
        throw new EmployeeNotFoundException("Такой сотрудник не найден");
    }

    @Override
    public String find(String firstName, String lastName) {
        Iterator<Employee> i = employees.iterator();
        while (i.hasNext()) {
            Employee e = i.next();
            if (e.getFullName().equals(firstName + ' ' + lastName)) {
                return e.toString();
            }
        }
        throw new EmployeeNotFoundException("Такой сотрудник не найден");
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees);
    }

}
