package com.project.loteria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "tb_contest")
public class Contest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String[] drawnNumbers;
    private Date date;

    @JsonIgnore
    @OneToMany(mappedBy = "contest", fetch = FetchType.LAZY)
    private Set<Result> results = new HashSet<>();

    public Contest(){}

    public Contest(Long id, String[] drawnNumbers, Date date) {
        this.id = id;
        this.drawnNumbers = drawnNumbers;
        this.date = date;
    }

    public void addResult(Result result){
        results.add(result);
        result.setContest(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Set<Result> getResults() {
        return results;
    }

    public void setResults(Set<Result> results) {
        this.results = results;
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
