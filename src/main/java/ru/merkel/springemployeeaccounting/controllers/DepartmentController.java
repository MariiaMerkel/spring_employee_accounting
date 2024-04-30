package ru.merkel.springemployeeaccounting.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.merkel.springemployeeaccounting.services.DepartmentService;

@RestController
@RequestMapping(value="/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(@Qualifier("mapBasedDepartmentServiceImpl") DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping(path = "/max-salary")
    public ResponseEntity<?> findByMaxSalaryOfDepartment(@RequestParam(value = "departmentId") Integer department) {
        return ResponseEntity.ok().body(departmentService.findByMaxSalaryOfDepartment(department));
    }
    @GetMapping(path = "/min-salary")
    public ResponseEntity<?> findByMinSalaryOfDepartment(@RequestParam(value = "departmentId") Integer department) {
        return ResponseEntity.ok().body(departmentService.findByMinSalaryOfDepartment(department));
    }
    @GetMapping(path = "/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(departmentService.findAll());
    }
    @GetMapping(path = "/all", params = "departmentId")
    public ResponseEntity<?> findByDepartment(@RequestParam(value = "departmentId",required = false) Integer department) {
        return ResponseEntity.ok().body(departmentService.findByDepartment(department));
    }
}
