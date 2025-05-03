package com.project.loteria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BetNumber {
    private UUID id;

    private int number;

    private boolean matched = false;

    @JsonIgnore
    private Set<Bet> bets = new HashSet<>() ;

    private Pool pool;

    public BetNumber(Pool pool, Bet bet, int number) {
        this.pool = pool;
        this.number = number;
        bets.add(bet);
    }

    public BetNumber(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Set<Bet> getBet() {
        return bets;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

}
