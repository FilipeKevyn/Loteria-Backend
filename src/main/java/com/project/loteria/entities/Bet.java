package com.project.loteria.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_bets")
public class Bet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String code;
    private String[] bet;
    @ManyToOne
    @JoinColumn(name = "contest_id")
    private Contest contest;

    @OneToOne(mappedBy = "bet", cascade = CascadeType.ALL)
    private Result result;
    private static final double valueInvested = 4.0;

    public Bet(){}

    public Bet(Long id, String code, String[] bet) {
        this.id = id;
        this.code = code;
        this.bet = bet;
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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
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
