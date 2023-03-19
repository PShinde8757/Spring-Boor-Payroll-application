package com.PS.payrollapplication.CustomException;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(Long id){
        super("Could Not Fount Employee "+id);
    }
}
