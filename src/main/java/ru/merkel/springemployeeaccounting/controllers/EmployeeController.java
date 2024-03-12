package ru.merkel.springemployeeaccounting.controllers;

import org.springframework.web.bind.annotation.*;
import ru.merkel.springemployeeaccounting.services.Employee;
import ru.merkel.springemployeeaccounting.services.EmployeeService;

import java.util.List;

@RestController
@RequestMapping(value="/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public Employee add(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.add(firstName, lastName);
    }

    @GetMapping(path = "/remove")
    public Employee remove(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.remove(firstName, lastName);
    }

    @GetMapping(path = "/find")
    public Employee find(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.find(firstName, lastName);
    }
    @GetMapping(path = "/findAll")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }
}
