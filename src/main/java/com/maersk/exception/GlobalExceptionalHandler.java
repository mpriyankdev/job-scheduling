package com.maersk.exception;

import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.ParseException;

@ControllerAdvice
public class GlobalExceptionalHandler {


    @ExceptionHandler({RuntimeException.class, ParseException.class, JsonParseException.class})
    public ResponseEntity handleAllExceptions() {
        return ResponseEntity.badRequest().body("Something went wrong. Please contact the system admin");
    }
}
