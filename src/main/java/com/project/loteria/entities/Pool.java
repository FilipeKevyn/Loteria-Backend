package com.project.loteria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.loteria.dtos.PoolDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Document(collection = "tb_pool")
public class Pool implements Serializable {
    @Id
    private String id;

    private String title;

    private String type;

    private double valueTotal;

    @JsonIgnore
    @DBRef
    private Contest contest;

    @JsonIgnore
    @DBRef
    private Set<Bet> bets = new HashSet<>();

    public Pool(String name){
        this.title = name;
    }

    public Pool(String id, Contest contest) {
        this.id = id;
        this.contest = contest;
    }

    public Pool(PoolDTO pool) {
        this.title = pool.title();
        this.type = pool.type();
    }

    public Pool(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
