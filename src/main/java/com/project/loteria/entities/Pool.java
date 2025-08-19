package com.project.loteria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.loteria.dtos.PoolDTO;
import jakarta.persistence.*;
import org.apache.commons.lang3.RandomStringUtils;

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

    @Column(unique = true)
    private String code = RandomStringUtils.randomAlphanumeric(6);

    private String title;

    private String type;

    private double valueTotal;

    @Column(name = "most_repeated_number")
    private Integer mostRepeatedNumber;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Contest contest;

    @JsonIgnore
    @OneToMany(mappedBy = "pool", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Bet> bets = new HashSet<>();

    @ManyToMany(mappedBy = "pools")
    private Set<User> users = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "pool")
    private Set<Number> numbers = new HashSet<>();


    public Pool(String name){
        this.title = name;
    }

    public Pool(Long id, Contest contest) {
        this.id = id;
        this.contest = contest;
    }

    public Pool(PoolDTO pool) {
        this.title = pool.title();
        this.type = pool.type();
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

    public Integer getMostRepeatedNumber(){ return mostRepeatedNumber;}

    public void setMostRepeatedNumber(Integer mostRepeatedNumber){this.mostRepeatedNumber = mostRepeatedNumber;}

    public String getCode() {
        return code;
    }

    public Set<Bet> getBets() {
        return bets;
    }

    public Set<Number> getNumbers() {
        return numbers;
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
