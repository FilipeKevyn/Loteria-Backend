package com.project.loteria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_numbers")
public class Number {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int number;

    private boolean matched = false;

    @JsonIgnore
    @ManyToMany(mappedBy = "betNumbers")
    private Set<Bet> bets = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "contest_id")
    private Contest contest;

    @ManyToOne
    @JoinColumn(name = "pool_id")
    private Pool pool;

    public Number(Pool pool, Bet bet, int number) {
        this.pool = pool;
        this.number = number;
        bets.add(bet);
    }

    public Number(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Set<Bet> getBet() {
        return bets;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }


}
