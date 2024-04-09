package ru.merkel.springemployeeaccounting.excaptions;

public class EmployeeInvalidate extends RuntimeException{
    public EmployeeInvalidate(String massage) {
        super(massage);
    }
}
