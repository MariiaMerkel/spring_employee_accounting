package ru.merkel.springemployeeaccounting.controllers;

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

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public ResponseEntity<?> add(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return ResponseEntity.ok().body(employeeService.add(firstName, lastName));
    }

    @GetMapping(path = "/remove")
    public ResponseEntity<?> remove(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return ResponseEntity.ok().body(employeeService.remove(firstName, lastName));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<?>  find(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return ResponseEntity.ok().body(employeeService.find(firstName, lastName));
    }
    @GetMapping(path = "/findAll")
    public Collection<Employee> findAll() {
        return employeeService.findAll();
    }
}
