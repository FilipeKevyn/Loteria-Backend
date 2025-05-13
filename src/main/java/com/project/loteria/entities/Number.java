package com.project.loteria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "tb_numbers")
public class Number {
    @Id
    private String id;

    private int number;

    private boolean matched = false;

    @JsonIgnore
    private Set<Bet> bets = new HashSet<>();

    private Contest contest;

    private Pool pool;

    public Number(Pool pool, Bet bet, int number) {
        this.pool = pool;
        this.number = number;
        bets.add(bet);
    }

    public Number(Pool pool, Contest contest, int number){
        this.pool = pool;
        this.contest = contest;
        this.number = number;
    }

    public Number(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
