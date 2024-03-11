package ru.merkel.springemployeeaccounting.services;

import com.fasterxml.jackson.core.JsonParseException;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
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

    @SneakyThrows
    public String add(String firstName, String lastName) {
        if (employees.size() >= counter) {
            throw new EmployeeStorageIsFullException("Не хватает места для добавления нового сотрудника");
        }
        try {
            find(firstName, lastName);
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть в списке");
        } catch (EmployeeNotFoundException ex) {
            try {
                employees.add(new Employee(firstName, lastName));
            } catch (RuntimeException rtex) {
                System.out.printf("%nДанные о сотруднике %s %s не добавлены%n", firstName, lastName);
                ex.getStackTrace();
            }
        }

        try {
            throw new JsonParseException("sonParseException");
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        }
        return "Сотрудник добавлен";
    }

    public void delete(String firstName, String lastName) {
        try {
            Employee employee = find(firstName, lastName);
            employees.remove(employee);
        } catch (EmployeeNotFoundException ex) {
            ex.getStackTrace();
        } catch (RuntimeException ex) {
            System.out.println("\nПроизошла ошибка.");
            ex.getStackTrace();
        }
    }

    public Employee find(String firstName, String lastName) {
        try {
            return (Employee) employees.stream().filter(e -> e.getFullName() == firstName + ' ' + lastName);
        } catch (RuntimeException ex) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    public void printAll() {
        employees.forEach(System.out::println);
    }
}
