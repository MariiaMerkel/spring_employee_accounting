package ru.merkel.springemployeeaccounting.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.merkel.springemployeeaccounting.services.EmployeeService;

@RestController
@RequestMapping(value="/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(@Qualifier("employeeServiceImpl") EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add/{firstName}/{lastName}/{salary}/{departmentId}")
    public ResponseEntity<?> add(@PathVariable String firstName, @PathVariable String lastName, @PathVariable Integer salary, @PathVariable Integer departmentId) {
        return ResponseEntity.ok().body(employeeService.add(firstName, lastName, salary, departmentId));
    }

    @GetMapping(path = "/remove/{firstName}/{lastName}")
    public ResponseEntity<?> remove(@PathVariable String firstName, @PathVariable String lastName) {
        return ResponseEntity.ok().body(employeeService.remove(firstName, lastName));
    }

    @GetMapping(path = "/find/{firstName}/{lastName}")
    public ResponseEntity<?> find(@PathVariable String firstName, @PathVariable String lastName) {
        return ResponseEntity.ok().body(employeeService.find(firstName, lastName));
    }

}
