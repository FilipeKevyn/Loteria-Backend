package com.project.loteria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.loteria.dtos.BetDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "tb_bet")
public class Bet implements Serializable {
    @Id
    private String id;
    private double valueInvested;

    @Transient
    private int quantityNumbers = 0;
    private List<Integer> betNumbers = new ArrayList<>();
    private int matched;
    private String gameType;
    @JsonIgnore
    @DBRef
    private Pool pool;

    public Bet(){}

    public Bet(BetDTO msBetDTO){
        this.betNumbers = msBetDTO.betNumbers();
        this.gameType = msBetDTO.gameType();
        setQuantityNumbers(betNumbers.size());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Integer> getBetNumbers() {
        return betNumbers;
    }

    public void setBetNumbers(List<Integer> betNumbers) {
        this.betNumbers = betNumbers;
    }

    public int getQuantityNumbers() {
        return quantityNumbers;
    }

    public void setQuantityNumbers(int quantityNumbers) {
        this.quantityNumbers = quantityNumbers;
    }

    public double getValueInvested() {
        return valueInvested;
    }

    public void setValueInvested(double valueInvested) {
        this.valueInvested = valueInvested;
    }

    public int getMatched() {
        return matched;
    }

    public void setMatched(int matched) {
        this.matched = matched;
    }

    public Pool getPool() {
        return pool;
    }

    public void setPool(Pool pool) {
        this.pool = pool;
    }

    public String getGameType() {
        return gameType;
    }

    public void setType() {
        this.gameType = pool.getType();
    }

}
