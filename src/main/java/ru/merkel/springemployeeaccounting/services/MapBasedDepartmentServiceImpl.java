package ru.merkel.springemployeeaccounting.services;

import org.springframework.stereotype.Service;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeNotFoundException;
import ru.merkel.springemployeeaccounting.models.Employee;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MapBasedDepartmentServiceImpl implements DepartmentService{

    private final MapBasedEmployeeServiceImpl employeeService;

    public MapBasedDepartmentServiceImpl(MapBasedEmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public String findByMaxSalaryOfDepartment(Integer department) {
        Collection<Employee> employeesDep = findByDepartment(department);
        Employee employee = employeesDep.stream().max(Comparator.comparing(Employee::getSalary)).orElseThrow(() -> new EmployeeNotFoundException("Ни один сотрудник в этом отделе не найден"));
        return String.format("Сотрудник с наибольшей зарплатой отдела №%d: %s", department, employee);
    }

    @Override
    public String findByMinSalaryOfDepartment(Integer department) {
        Collection<Employee> employeesDep = findByDepartment(department);
        Employee employee = employeesDep.stream().min(Comparator.comparing(Employee::getSalary)).orElseThrow(() -> new EmployeeNotFoundException("Ни один сотрудник в этом отделе не найден"));
        return String.format("Сотрудник с наименьшей зарплатой отдела №%d: %s", department, employee);
    }

    @Override
    public Collection<Employee> findByDepartment(Integer department) {
        return employeeService.findAll().stream().filter(e -> e.getDepartment().equals(department)).collect(Collectors.toSet());
    }

    @Override
    public Map<Integer, List<Employee>> findAll() {
        return employeeService.findAll().stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
