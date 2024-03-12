package ru.merkel.springemployeeaccounting.excaptions;

public class EmployeeStorageIsFullException extends RuntimeException{

    public EmployeeStorageIsFullException(String massage) {
        super(massage);
    }
}
