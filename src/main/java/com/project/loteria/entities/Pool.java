package com.project.loteria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.loteria.dtos.PoolDTO;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Pool implements Serializable {

    private UUID id;

    private String code = RandomStringUtils.randomAlphanumeric(6);

    private String title;

    private String type;

    private double valueTotal;

    @JsonIgnore
    private Contest contest;

    @JsonIgnore
    private Set<Bet> bets = new HashSet<>();

    private Set<User> users = new HashSet<>();

    @JsonIgnore
    private Set<BetNumber> betNumbers = new HashSet<>();


    public Pool(String name){
        this.title = name;
    }

    public Pool(UUID id, Contest contest) {
        this.id = id;
        this.contest = contest;
    }

    public Pool(PoolDTO pool) {
        this.title = pool.title();
        this.type = pool.type();
    }

    public Pool(){

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getCode() {
        return code;
    }

    public Set<Bet> getBets() {
        return bets;
    }

    public Set<BetNumber> getBetNumbers() {
        return betNumbers;
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
