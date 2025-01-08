package com.project.loteria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.loteria.dtos.BetDTO;
import com.project.loteria.service.MathService;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_bets")
public class Bet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String code;
    private double valueInvested;
    private int quantityNumbers;
    private List<Integer> bet = new ArrayList<>();
    private int matched;
    @ManyToOne
    @JoinColumn(name = "contest_id")
    private Contest contest;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pool_id")
    private Pool pool;

    public Bet(){}

    public Bet(Long id, String code, List<Integer> bet, int quantityNumbers) {
        this.id = id;
        this.code = code;
        this.bet = bet;
        this.quantityNumbers = quantityNumbers;
        valueInvested = MathService.combination(quantityNumbers, 6) * 5;
    }

    public Bet(BetDTO msBetDTO){
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

    public List<Integer> getBet() {
        return bet;
    }

    public void setBet(List<Integer> bet) {
        this.bet = bet;
    }

    public int getQuantityNumbers() {
        return bet.size();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bet bet = (Bet) o;
        return Objects.equals(id, bet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
