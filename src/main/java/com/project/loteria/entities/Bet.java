package com.project.loteria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.loteria.dtos.BetDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "tb_bets")
public class Bet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private double valueInvested;
    private int quantityNumbers = 0;

    @JsonIgnore
    private List<Integer> betNumbersArray = new ArrayList<>();

    @OneToMany(mappedBy = "bet", cascade = CascadeType.ALL)
    private Set<BetNumber> betNumbers = new HashSet<>();
    private int matched;
    private String gameType;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pool_id")
    private Pool pool;

    public Bet(){}

    public Bet(List<Integer> betNumbers, String gameType){
        this.betNumbersArray = betNumbers;
        this.gameType = gameType;
    }

    public Bet(BetDTO msBetDTO){
        this.betNumbersArray = msBetDTO.betNumbers();
        this.gameType = msBetDTO.gameType();
        setQuantityNumbers(betNumbersArray.size());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Integer> getBetNumbersArray() {
        return betNumbersArray;
    }

    public void setBetNumbersArray(List<Integer> betNumbersArray) {
        this.betNumbersArray = betNumbersArray;
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

    public void setType(String type) {
        this.gameType = type;
    }

    public Set<BetNumber> getBetNumbers() {
        return betNumbers;
    }
}
