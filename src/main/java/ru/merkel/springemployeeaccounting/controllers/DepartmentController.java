package ru.merkel.springemployeeaccounting.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.merkel.springemployeeaccounting.services.DepartmentService;

@RestController
@RequestMapping(value="/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(@Qualifier("mapBasedDepartmentServiceImpl") DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping(path = "/max-salary/{departmentId}")
    public ResponseEntity<?> findByMaxSalaryOfDepartment(@PathVariable Integer departmentId) {
        return ResponseEntity.ok().body(departmentService.findByMaxSalaryOfDepartment(departmentId));
    }
    @GetMapping(path = "/min-salary/{departmentId}")
    public ResponseEntity<?> findByMinSalaryOfDepartment(@PathVariable Integer departmentId) {
        return ResponseEntity.ok().body(departmentService.findByMinSalaryOfDepartment(departmentId));
    }
    @GetMapping(path = "/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(departmentService.findAll());
    }
    @GetMapping(path = "/all/{departmentId}")
    public ResponseEntity<?> findByDepartment(@PathVariable(required = false) Integer departmentId) {
        return ResponseEntity.ok().body(departmentService.findByDepartment(departmentId));
    }
}
