package com.project.loteria.exceptions;

public class BetNotFoundException extends IllegalArgumentException{
    public BetNotFoundException(){
        super("Bet não existente");
    }
}
