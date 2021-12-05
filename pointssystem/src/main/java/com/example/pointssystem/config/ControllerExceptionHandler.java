package com.example.pointssystem.config;

import com.example.pointssystem.domain.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ControllerExceptionHandler {

    /*
        intercepts IllegalArgumentException exception and returns ErrorMessage format
    */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    ErrorMessage exceptionHandler(IllegalArgumentException e) {
        return new ErrorMessage("400", e.getMessage());
    }


}
