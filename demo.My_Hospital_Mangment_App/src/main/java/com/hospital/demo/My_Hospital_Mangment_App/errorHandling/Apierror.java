package com.hospital.demo.My_Hospital_Mangment_App.errorHandling;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Data
public class Apierror {
    private LocalDateTime localDateTime=LocalDateTime.now();
    private  HttpStatus httpstatus;
    private String message;

}
