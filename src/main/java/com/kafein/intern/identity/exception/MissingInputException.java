package com.kafein.intern.identity.exception;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MissingInputException {

    public void handleMissigParams(MissingServletRequestParameterException ex){
        String name = ex.getParameterName();
        System.out.println(name + " parameter is missing");
    }
}
