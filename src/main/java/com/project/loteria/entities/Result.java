package com.project.loteria.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private int matched;
    @ManyToOne
    @JoinColumn(name = "contest_id")
    private Contest contest;
    @OneToOne
    private Bet bet;
    public Result(){}

    public Result(UUID id, int matched) {
        this.id = id;
        this.matched = matched;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getMatched() {
        return matched;
    }

    public void setMatched(int matched) {
        this.matched = matched;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result results = (Result) o;
        return Objects.equals(id, results.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
