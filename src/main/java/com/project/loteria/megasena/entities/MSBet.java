package com.project.loteria.megasena.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_bets_megasena'")
public class MSBet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String code;
    private String[] bet;
    @ManyToOne
    @JoinColumn(name = "contest_id")
    private MSContest contest;

    @JsonIgnore
    @OneToOne(mappedBy = "bet", cascade = CascadeType.ALL)
    private MSResult result;
    private static final double valueInvested = 4.0;

    public MSBet(){}

    public MSBet(Long id, String code, String[] bet) {
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

    public MSContest getContest() {
        return contest;
    }

    public void setContest(MSContest contest) {
        this.contest = contest;
    }

    public MSResult getResult() {
        return result;
    }

    public void setResult(MSResult result) {
        this.result = result;
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
