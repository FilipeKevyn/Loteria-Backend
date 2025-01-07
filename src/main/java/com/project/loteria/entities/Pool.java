package com.project.loteria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.loteria.megasena.dtos.MSPoolDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_pool")
public class Pool implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private double valueTotal;

    @JsonIgnore
    @OneToOne
    private Contest contest;

    @JsonIgnore
    @OneToMany(mappedBy = "pool", fetch = FetchType.EAGER)
    private Set<Bet> bets = new HashSet<>();

    public Pool(String name){
        this.title = name;
    }

    public Pool(Long id, Contest contest) {
        this.id = id;
        this.contest = contest;
    }

    public Pool(MSPoolDTO pool) {
        this.title = pool.title();
    }

    public Pool(){

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

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public double getValueTotal() {
        return valueTotal;
    }

    public void setValueTotal(double valueTotal) {
        this.valueTotal = valueTotal;
    }

    public Set<Bet> getBets() {
        return bets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pool msPool = (Pool) o;
        return Objects.equals(id, msPool.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
