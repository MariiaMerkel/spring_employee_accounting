package ru.merkel.springemployeeaccounting.excaptions;

public class EmployeeAlreadyAddedException extends RuntimeException{
    public EmployeeAlreadyAddedException(String massage) {
        super(massage);
    }
}
