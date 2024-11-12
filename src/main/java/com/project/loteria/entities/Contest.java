package com.project.loteria.entities;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "tb_contest")
public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String[] drawnNumbers;
    private Date date;
    @OneToMany(mappedBy = "contest")
    private Set<Bet> bets = new HashSet<>();
    @OneToMany(mappedBy = "contest")
    private Set<Result> results = new HashSet<>();

    public Contest(){}

    public Contest(UUID id, String[] drawnNumbers, Date date) {
        this.id = id;
        this.drawnNumbers = drawnNumbers;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String[] getDrawnNumbers() {
        return drawnNumbers;
    }

    public void setDrawnNumbers(String[] drawnNumbers) {
        this.drawnNumbers = drawnNumbers;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Bet> getBets() {
        return bets;
    }

    public Set<Result> getResults() {
        return results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contest contest = (Contest) o;
        return Objects.equals(id, contest.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
