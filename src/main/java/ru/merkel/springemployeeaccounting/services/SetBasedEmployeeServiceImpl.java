package ru.merkel.springemployeeaccounting.services;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeAlreadyAddedException;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeNotFoundException;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeStorageIsFullException;
import ru.merkel.springemployeeaccounting.models.Employee;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SetBasedEmployeeServiceImpl implements EmployeeService {

    private final Set<Employee> employees = new HashSet<>();
    private static final int COUNTER = 5;

    @SneakyThrows
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

    @SneakyThrows
    @Override
    public String remove(String firstName, String lastName, Integer salary, Integer department) {
        Employee e = new Employee(firstName, lastName, salary, department);
        boolean removed = employees.remove(e);
        if (!removed) {
            throw new EmployeeNotFoundException("Такой сотрудник не найден");
        }
        return String.format("Удалён сотрудник: %s.", e.getFullName());
    }

    @SneakyThrows
    @Override
    public String find(String firstName, String lastName, Integer salary, Integer department) {
        Employee e = new Employee(firstName, lastName, salary, department);
        Optional<Employee> found = employees.stream().filter(employee -> employee.getFullName().equals(e.getFullName())).findFirst();
        if (found.isEmpty()) {
            throw new EmployeeNotFoundException("Такой сотрудник не найден");
        }
        return found.toString();
    }

    @SneakyThrows
    @Override
    public String findByMaxSalaryOfDepartment(Integer department) {
        Set<Employee> employeesDep = findByDepartment(department);
        Employee employee = employeesDep.stream().max(Comparator.comparing(Employee::getSalary)).orElseThrow();
        return String.format("Сотрудник с наибольшей зарплатой отдела №%d: %s", department, employee);
    }

    @SneakyThrows
    @Override
    public String findByMinSalaryOfDepartment(Integer department) {
        Set<Employee> employeesDep = findByDepartment(department);
        Employee employee = employeesDep.stream().min(Comparator.comparing(Employee::getSalary)).orElseThrow();
        return String.format("Сотрудник с наименьшей зарплатой отдела №%d: %s", department, employee);
    }

    @SneakyThrows
    @Override
    public Set<Employee> findByDepartment(Integer department) {
        return employees.stream().filter(e -> e.getDepartment() == department).collect(Collectors.toSet());
    }

    @SneakyThrows
    @Override
    public Collection<List<Employee>> findAll(Integer department) {
        if (department == null) {
            Map<Integer, List<Employee>> groupedByDep = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));
            return Collections.unmodifiableCollection(groupedByDep.values());
        } else {
            Map<Integer, List<Employee>> groupedByDep = employees.stream().filter(e -> e.getDepartment() == department).collect(Collectors.groupingBy(Employee::getDepartment));
            return Collections.unmodifiableCollection(groupedByDep.values());
        }
    }
}
