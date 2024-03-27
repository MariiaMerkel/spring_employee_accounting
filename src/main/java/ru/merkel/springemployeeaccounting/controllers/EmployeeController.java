package ru.merkel.springemployeeaccounting.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.merkel.springemployeeaccounting.models.Employee;
import ru.merkel.springemployeeaccounting.services.EmployeeService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(@Qualifier("setBasedEmployeeServiceImpl") EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public ResponseEntity<?> add(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("salary") Integer salary, @RequestParam("department") Integer department) {
        return ResponseEntity.ok().body(employeeService.add(firstName, lastName, salary, department));
    }

    @GetMapping(path = "/remove")
    public ResponseEntity<?> remove(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("salary") Integer salary, @RequestParam("department") Integer department) {
        return ResponseEntity.ok().body(employeeService.remove(firstName, lastName, salary, department));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<?> find(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("salary") Integer salary, @RequestParam("department") Integer department) {
        return ResponseEntity.ok().body(employeeService.find(firstName, lastName, salary, department));
    }

    @GetMapping(path = "/findByMaxSalaryOfDepartment")
    public ResponseEntity<?> findByMaxSalaryOfDepartment(@RequestParam("department") Integer department) {
        return ResponseEntity.ok().body(employeeService.findByMaxSalaryOfDepartment(department));
    }

    @GetMapping(path = "/findAll")
    public Collection<Employee> findAll() {
        return employeeService.findAll();
    }
}
