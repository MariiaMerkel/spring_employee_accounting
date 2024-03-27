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
    public ResponseEntity<?> add(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName, @RequestParam(value = "salary") Integer salary, @RequestParam(value = "department") Integer department) {
        return ResponseEntity.ok().body(employeeService.add(firstName, lastName, salary, department));
    }

    @GetMapping(path = "/remove")
    public ResponseEntity<?> remove(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName, @RequestParam(value = "salary") Integer salary, @RequestParam(value = "department") Integer department) {
        return ResponseEntity.ok().body(employeeService.remove(firstName, lastName, salary, department));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<?> find(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName, @RequestParam(value = "salary") Integer salary, @RequestParam(value = "department") Integer department) {
        return ResponseEntity.ok().body(employeeService.find(firstName, lastName, salary, department));
    }

    @GetMapping(path = "/max-salary")
    public ResponseEntity<?> findByMaxSalaryOfDepartment(@RequestParam(value = "department") Integer department) {
        return ResponseEntity.ok().body(employeeService.findByMaxSalaryOfDepartment(department));
    }
    @GetMapping(path = "/min-salary")
    public ResponseEntity<?> findByMinSalaryOfDepartment(@RequestParam(value = "department") Integer department) {
        return ResponseEntity.ok().body(employeeService.findByMinSalaryOfDepartment(department));
    }
    @GetMapping(path = "/all")
    public ResponseEntity<?> findByDepartment(@RequestParam(value = "departmentId", required = false) Integer department) {
        return ResponseEntity.ok().body(employeeService.findAll(department));
    }
}
