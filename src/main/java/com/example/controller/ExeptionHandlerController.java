package com.example.controller;

import com.example.exp.AppBadException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ExeptionHandlerController {
    @ExceptionHandler(AppBadException.class)
    private ResponseEntity<?> handle(AppBadException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<?>handle(RuntimeException e){
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
}
