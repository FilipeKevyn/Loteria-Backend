package com.project.loteria.exceptions.handler;

import com.project.loteria.exceptions.BetAlreadyExistsException;
import com.project.loteria.exceptions.BetNotFoundException;
import com.project.loteria.exceptions.PoolNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PoolNotFoundException.class)
    public ResponseEntity<RestErrorMensage> poolNotFoundHandler(PoolNotFoundException e) {
        RestErrorMensage response = new RestErrorMensage(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(BetAlreadyExistsException.class)
    public ResponseEntity<RestErrorMensage> betAlredyExistsHandler(BetAlreadyExistsException e){
        RestErrorMensage response = new RestErrorMensage(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(BetNotFoundException.class)
    public ResponseEntity<RestErrorMensage> betNotFoundHandler(BetNotFoundException e){
        RestErrorMensage response = new RestErrorMensage(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
