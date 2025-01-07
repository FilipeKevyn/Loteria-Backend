package com.project.loteria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.loteria.megasena.dtos.MSContestDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "tb_contest")
public class Contest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer[] drawnNumbers = new Integer[6];
    private String codeContest;


    @JsonIgnore
    @OneToOne
    private Pool pool;

    public Contest(){}

    public Contest(Long id, Integer[] drawnNumbers, String codeContest) {
        this.id = id;
        this.drawnNumbers = drawnNumbers;
        this.codeContest = codeContest;
    }

    public Contest(MSContestDTO msContestDTO){
        this.id = msContestDTO.id();
        this.drawnNumbers = msContestDTO.drawNumbers();
        this.codeContest = msContestDTO.codeContest();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer[] getDrawnNumbers() {
        return drawnNumbers;
    }

    public void setDrawnNumbers(Integer[] drawnNumbers) {
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
