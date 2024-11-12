package com.project.loteria.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_bets")
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true, nullable = false)
    private String code;
    private String[] bet;
    @ManyToOne
    @JoinColumn(name = "contest_id")
    private Contest contest;

    @OneToOne
    private Result result;
    private static final double valueInvested = 4.0;

    public Bet(){}

    public Bet(UUID id, String code, String[] bet) {
        this.id = id;
        this.code = code;
        this.bet = bet;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String[] getBet() {
        return bet;
    }

    public void setBet(String[] bet) {
        this.bet = bet;
    }

    public static double getValueInvested() {
        return valueInvested;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
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
