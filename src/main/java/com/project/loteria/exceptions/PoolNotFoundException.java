package com.project.loteria.exceptions;

import java.util.UUID;

public class PoolNotFoundException extends RuntimeException {
    public PoolNotFoundException(UUID id){
        super(String.format("Pool %d not found", id));
    }
}
