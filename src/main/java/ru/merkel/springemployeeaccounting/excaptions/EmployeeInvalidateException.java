package ru.merkel.springemployeeaccounting.excaptions;

public class EmployeeInvalidateException extends RuntimeException{
    public EmployeeInvalidateException(String massage) {
        super(massage);
    }
}
