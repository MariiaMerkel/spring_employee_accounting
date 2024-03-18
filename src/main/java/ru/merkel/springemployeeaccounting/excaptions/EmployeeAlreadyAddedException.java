package ru.merkel.springemployeeaccounting.excaptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)

public class EmployeeAlreadyAddedException extends RuntimeException{

    public EmployeeAlreadyAddedException(String massage) {
        super(massage);
    }
}
