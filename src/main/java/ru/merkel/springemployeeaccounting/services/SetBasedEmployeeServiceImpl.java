package ru.merkel.springemployeeaccounting.services;

import org.springframework.stereotype.Service;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeAlreadyAddedException;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeInvalidateException;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeNotFoundException;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeStorageIsFullException;
import ru.merkel.springemployeeaccounting.models.Employee;
import java.util.*;
import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
public class SetBasedEmployeeServiceImpl implements EmployeeService {

    private final Set<Employee> employees = new HashSet<>();
    private static final int COUNTER = 5;

    @Override
    public String add(String firstName, String lastName, Integer salary, Integer department) {
        if (employees.size() >= COUNTER) {
            throw new EmployeeStorageIsFullException("Список заполнен, добавлять новых сотрудников нельзя");
        }
        validateName(firstName, lastName);
        Employee e = new Employee(firstName, lastName, salary, department);
        boolean added = employees.add(e);
        if (!added) {
            throw new EmployeeAlreadyAddedException("Сотрудник с именем " + e.getFullName() + " уже есть в списке");
        }
        return String.format("Добавлен новый сотрудник: %s.", e.getFullName());
    }

    @Override
    public String remove(String firstName, String lastName) {
        String key = validateName(firstName, lastName);
        Iterator<Employee> i = employees.iterator();
        while (i.hasNext()) {
            if (i.next().getFullName().equals(key)) {
                i.remove();
                return String.format("Удалён сотрудник: %s.", key);
            }
        }
        throw new EmployeeNotFoundException("Такой сотрудник не найден");
    }

    @Override
    public String find(String firstName, String lastName) {
        String key = validateName(firstName, lastName);
        Iterator<Employee> i = employees.iterator();
        while (i.hasNext()) {
            Employee e = i.next();
            if (e.getFullName().equals(key)) {
                return e.toString();
            }
        }
        throw new EmployeeNotFoundException("Такой сотрудник не найден");
    }

    @Override
    public String validateName(String firstName, String lastName) {
        if (!isAlpha(firstName) || !isAlpha(lastName)) {
            throw new EmployeeInvalidateException("Некорректное имя или фамилия");
        }
        return capitalize(firstName.toLowerCase()) + ' ' + capitalize(lastName.toLowerCase());
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees);
    }

}
