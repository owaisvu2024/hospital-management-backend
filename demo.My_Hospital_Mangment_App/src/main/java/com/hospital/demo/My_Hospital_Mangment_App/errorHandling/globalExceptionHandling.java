package com.hospital.demo.My_Hospital_Mangment_App.errorHandling;

import io.jsonwebtoken.JwtException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class globalExceptionHandling {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Apierror>validException(MethodArgumentNotValidException e){
  String errorMessage=e.getBindingResult().getFieldError().getDefaultMessage();
  Apierror apierror=new Apierror();
  apierror.setMessage(errorMessage);
  apierror.setHttpstatus(HttpStatus.BAD_REQUEST);
  return new ResponseEntity<>(apierror,apierror.getHttpstatus());

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Apierror> usernameNotfound(ResourceNotFoundException e){
        Apierror apierror=new Apierror();
        apierror.setMessage(e.getMessage());
        apierror.setHttpstatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apierror,apierror.getHttpstatus());
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Apierror> accessDenied(AccessDeniedException e){
        Apierror apierror=new Apierror();
        apierror.setMessage(e.getMessage());
        apierror.setHttpstatus(HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(apierror,apierror.getHttpstatus());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Apierror> exception(Exception e){
        Apierror apierror=new Apierror();
        apierror.setMessage(e.getMessage());
        apierror.setHttpstatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(apierror,apierror.getHttpstatus());

    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Apierror> authentication(AuthenticationException e){
        Apierror apierror=new Apierror();
        apierror.setMessage(e.getMessage());
        apierror.setHttpstatus(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(apierror,apierror.getHttpstatus());
    }
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<Apierror> jwtException(JwtException e){
        Apierror apierror=new Apierror();
        apierror.setMessage(e.getMessage());
        apierror.setHttpstatus(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(apierror,apierror.getHttpstatus());
    }

}
