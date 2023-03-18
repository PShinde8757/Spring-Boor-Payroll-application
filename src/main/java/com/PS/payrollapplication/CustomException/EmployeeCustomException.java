package com.PS.payrollapplication.CustomException;

public class EmployeeCustomException extends RuntimeException{
    public EmployeeCustomException(Long id){
        super("Could Not Fount Employee "+id);
    }
}
