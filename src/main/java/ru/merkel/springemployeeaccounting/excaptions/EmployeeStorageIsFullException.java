package ru.merkel.springemployeeaccounting.excaptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmployeeStorageIsFullException extends RuntimeException{

    public EmployeeStorageIsFullException(String massage) {
        super(massage);
    }
}
