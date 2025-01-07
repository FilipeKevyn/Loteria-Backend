package com.project.loteria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.loteria.megasena.dtos.MSPoolDTO;
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

    private String title;

    private double valueTotal;

    @JsonIgnore
    @OneToOne
    private MSContest contest;

    @JsonIgnore
    @OneToMany(mappedBy = "pool", fetch = FetchType.EAGER)
    private Set<MSBet> bets = new HashSet<>();

    public MSPool(String name){
        this.title = name;
    }

    public MSPool(Long id, MSContest contest) {
        this.id = id;
        this.contest = contest;
    }

    public MSPool(MSPoolDTO pool) {
        this.title = pool.title();
    }

    public MSPool(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
