package com.PS.payrollapplication.CustomException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


/**
 * @ResponseBody signals that this advice is rendered straight into the response body.
 *
 * @ExceptionHandler configures the advice to only respond if an EmployeeNotFoundException is thrown.
 *
 * @ResponseStatus says to issue an HttpStatus.NOT_FOUND, i.e. an HTTP 404.
 *
 * The body of the advice generates the content. In this case, it gives the message of the exception.
 */


@ControllerAdvice
public class EmployeeExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(EmployeeNotFoundException msg){
        return  msg.getMessage();
    }
}
