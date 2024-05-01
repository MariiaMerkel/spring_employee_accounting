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

    @GetMapping(path = "/{id}/salary/sum")
    public ResponseEntity<?> sumSalaryOfDepartment(@PathVariable Integer id) {
        return ResponseEntity.ok().body(departmentService.sumSalaryOfDepartment(id));
    }
    @GetMapping(path = "/{id}/salary/max")
    public ResponseEntity<?> findByMaxSalaryOfDepartment(@PathVariable Integer id) {
        return ResponseEntity.ok().body(departmentService.findByMaxSalaryOfDepartment(id));
    }
    @GetMapping(path = "/{id}/salary/min")
    public ResponseEntity<?> findByMinSalaryOfDepartment(@PathVariable Integer id) {
        return ResponseEntity.ok().body(departmentService.findByMinSalaryOfDepartment(id));
    }
    @GetMapping(path = "/employees")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(departmentService.findAll());
    }

    @GetMapping(path = "/{id}/employees")
    public ResponseEntity<?> findByDepartment(@PathVariable Integer id) {
        return ResponseEntity.ok().body(departmentService.findByDepartment(id));
    }
}
