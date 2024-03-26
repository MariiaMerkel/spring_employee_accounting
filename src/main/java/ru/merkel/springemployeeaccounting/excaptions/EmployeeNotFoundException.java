package ru.merkel.springemployeeaccounting.excaptions;

public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(String massage) {
        super(massage);
    }
}
