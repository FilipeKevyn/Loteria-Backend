package com.project.loteria.megasena.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_pool_megasena")
public class MSPool implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double valueTotal;

    @OneToOne
    private MSContest contest;

    @OneToMany(mappedBy = "pool", fetch = FetchType.EAGER)
    private Set<MSBet> bets = new HashSet<>();

    @OneToMany(mappedBy = "pool", fetch = FetchType.EAGER)
    private Set<MSResult> results = new HashSet<>();

    public MSPool(Long id, MSContest contest) {
        this.id = id;
        this.contest = contest;
    }

    public MSPool(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MSContest getContest() {
        return contest;
    }

    public void setContest(MSContest contest) {
        this.contest = contest;
    }

    public double getValueTotal() {
        return valueTotal;
    }

    public void setValueTotal(double valueTotal) {
        this.valueTotal = valueTotal;
    }

    public Set<MSResult> getResults() {
        return results;
    }

    public Set<MSBet> getBets() {
        return bets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MSPool msPool = (MSPool) o;
        return Objects.equals(id, msPool.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
