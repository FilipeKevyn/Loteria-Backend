package com.project.loteria.exceptions.handler;

import com.project.loteria.exceptions.BetNotFoundException;
import com.project.loteria.exceptions.PoolNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BetNotFoundException.class)
    public ResponseEntity<String> betNotFoundHandler(BetNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(PoolNotFoundException.class)
    public ResponseEntity<String> poolNotFoundHandler(BetNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
