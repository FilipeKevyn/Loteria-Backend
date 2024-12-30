package com.project.loteria.exceptions;

public class BetNotFoundException extends RuntimeException{
    public BetNotFoundException(long id){
        super(String.format("Bet %d not found", id));
    }
}
