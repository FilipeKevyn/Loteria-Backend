package com.project.loteria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Number {
    private UUID id;

    private int number;

    private boolean matched = false;

    @JsonIgnore
    private Set<Bet> bets = new HashSet<>() ;

    private Pool pool;

    private Contest contest;

    public Number(UUID id, Pool pool, Bet bet, int number, Contest contest) {
        this.pool = pool;
        this.number = number;
        bets.add(bet);
    }

    public Number(){}

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

    public Set<Bet> getBets() {
        return bets;
    }

    public void setBets(Set<Bet> bets) {
        this.bets = bets;
    }

    public Pool getPool() {
        return pool;
    }

    public void setPool(Pool pool) {
        this.pool = pool;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }
}
