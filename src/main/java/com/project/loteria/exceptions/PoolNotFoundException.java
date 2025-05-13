package com.project.loteria.exceptions;

public class PoolNotFoundException extends RuntimeException {
    public PoolNotFoundException(String id){
        super(String.format("Pool %d not found", id));
    }
}
