package com.project.loteria.exceptions;

public class BetAlreadyExistsException extends IllegalArgumentException{
    public BetAlreadyExistsException(){
        super("Aposta já existente");
    }
}
