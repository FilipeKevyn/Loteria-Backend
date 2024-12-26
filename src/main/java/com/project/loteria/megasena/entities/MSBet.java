package com.project.loteria.megasena.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.loteria.megasena.dtos.MSBetDTO;
import com.project.loteria.service.MathService;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_bets_megasena")
public class MSBet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String code;
    private double valueInvested;
    private int quantityNumbers;
    private Integer[] bet = new Integer[quantityNumbers];
    private int matched;
    @ManyToOne
    @JoinColumn(name = "contest_id")
    private MSContest contest;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pool_id")
    private MSPool pool;

    public MSBet(){}

    public MSBet(Long id, String code, Integer[] bet, int quantityNumbers) {
        this.id = id;
        this.code = code;
        this.bet = bet;
        this.quantityNumbers = quantityNumbers;
        valueInvested = MathService.combination(quantityNumbers, 6) * 5;
    }

    public MSBet(MSBetDTO msBetDTO){
        this.id = msBetDTO.id();
        this.code = msBetDTO.code();
        this.bet = msBetDTO.bet();
        this.quantityNumbers = msBetDTO.quantityNumbers();
        valueInvested = MathService.combination(quantityNumbers, 6) * 5;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer[] getBet() {
        return bet;
    }

    public void setBet(Integer[] bet) {
        this.bet = bet;
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

    public MSPool getPool() {
        return pool;
    }

    public void setPool(MSPool pool) {
        this.pool = pool;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MSBet bet = (MSBet) o;
        return Objects.equals(id, bet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
