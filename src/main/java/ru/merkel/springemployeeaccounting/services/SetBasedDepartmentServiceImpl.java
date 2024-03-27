package ru.merkel.springemployeeaccounting.services;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.merkel.springemployeeaccounting.models.Employee;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SetBasedDepartmentServiceImpl implements DepartmentService {

    private final Set<Employee> employees = new HashSet<>();
    private static final int COUNTER = 5;

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
