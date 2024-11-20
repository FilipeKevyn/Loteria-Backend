package com.project.loteria.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int matched;
    @ManyToOne
    @JoinColumn(name = "contest_id")
    private Contest contest;
    @OneToOne
    private Bet bet;
    public Result(){}

    public Result(Long id, int matched) {
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
