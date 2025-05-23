package com.project.loteria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.loteria.dtos.ContestDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "tb_contest")
public class Contest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private List<Integer> drawnNumbers = new ArrayList<>();
    private String codeContest;

    @OneToMany(mappedBy = "contest")
    private Set<Number> numbers = new HashSet<>();

    @JsonIgnore
    @OneToOne
    private Pool pool;

    public Contest(){}

    public Contest(UUID id, List<Integer> drawnNumbers, String codeContest) {
        this.id = id;
        this.drawnNumbers = drawnNumbers;
        this.codeContest = codeContest;
    }

    public Contest(ContestDTO msContestDTO){
        this.drawnNumbers = msContestDTO.drawNumbers();
        this.codeContest = msContestDTO.codeContest();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Integer> getDrawnNumbers() {
        return drawnNumbers;
    }

    public void setDrawnNumbers(List<Integer> drawnNumbers) {
        this.drawnNumbers = drawnNumbers;
    }

    public String getCodeContest() {
        return codeContest;
    }

    public void setCodeContest(String codeContest) {
        this.codeContest = codeContest;
    }

    public Pool getPool() {
        return pool;
    }

    public void setPool(Pool pool) {
        this.pool = pool;
    }

    public Set<Number> getNumbers() {
        return numbers;
    }

    public void setNumbers(Set<Number> numbers) {
        this.numbers = numbers;
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
