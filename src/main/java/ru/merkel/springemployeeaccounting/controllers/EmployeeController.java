package ru.merkel.springemployeeaccounting.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeStorageIsFullException;
import ru.merkel.springemployeeaccounting.services.Employee;
import ru.merkel.springemployeeaccounting.services.EmployeeService;

@RestController
@RequestMapping(value="/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> add(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName){

        String response = employeeService.add(firstName, lastName);
        return ResponseEntity.ok().body(response);
//        String response = "";
//        try {
//            response = employeeService.add(firstName, lastName);
//        } catch (EmployeeStorageIsFullException e) {
//            e.getStackTrace();
//            response = e.getMessage();
//        }
//        return response;

//        EmployeeStorageIsFullException ex = new EmployeeStorageIsFullException();
//        try {
//            return employeeService.add(firstName, lastName);
//        } catch (EmployeeStorageIsFullException e) {
//            e.getStackTrace();
//        }
//        return ex.getMessage();
    }
}
