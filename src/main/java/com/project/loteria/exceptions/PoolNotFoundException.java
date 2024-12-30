package com.project.loteria.exceptions;

public class PoolNotFoundException extends RuntimeException {
    public PoolNotFoundException(long id){
        super(String.format("Pool %d not found", id));
    }
}
