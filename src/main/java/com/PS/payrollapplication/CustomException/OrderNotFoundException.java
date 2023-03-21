package com.PS.payrollapplication.CustomException;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(Long id){
        super("Could Not Find Order "+ id);
    }
}
