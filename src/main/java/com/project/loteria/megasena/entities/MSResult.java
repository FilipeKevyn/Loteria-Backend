package com.project.loteria.megasena.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_results_megasena")
public class MSResult implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int matched;
    @ManyToOne
    @JoinColumn(name = "contest_id")
    private MSContest contest;
    @OneToOne
    @JoinColumn(name = "bet_id")
    private MSBet bet;
    public MSResult(){}

    public MSResult(Long id, int matched) {
        this.id = id;
        this.matched = matched;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMatched() {
        return matched;
    }

    public void setMatched(int matched) {
        this.matched = matched;
    }

    public MSContest getContest() {
        return contest;
    }

    public void setContest(MSContest contest) {
        this.contest = contest;
    }

    public MSBet getBet() {
        return bet;
    }

    public void setBet(MSBet bet) {
        this.bet = bet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MSResult results = (MSResult) o;
        return Objects.equals(id, results.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
