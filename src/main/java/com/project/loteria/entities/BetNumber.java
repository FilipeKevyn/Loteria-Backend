package com.project.loteria.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_bet_numbers")
public class BetNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int number;

    private boolean matched = false;

    @ManyToOne
    private Bet bet;

    public BetNumber(int number, Bet bet) {
        this.number = number;
        this.bet = bet;
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

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }
}
