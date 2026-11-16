package com.hospital.demo.My_Hospital_Mangment_App.errorHandling;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
