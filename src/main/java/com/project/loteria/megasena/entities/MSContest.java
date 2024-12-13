package com.project.loteria.megasena.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.loteria.megasena.dtos.MSContestDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "tb_contest_megasena")
public class MSContest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer[] drawnNumbers = new Integer[6];
    private Date date;


    @OneToOne
    private MSPool pool;

    public MSContest(){}

    public MSContest(Long id, Integer[] drawnNumbers, Date date) {
        this.id = id;
        this.drawnNumbers = drawnNumbers;
        this.date = date;
    }

    public MSContest(MSContestDTO msContestDTO){
        this.id = msContestDTO.id();
        this.drawnNumbers = msContestDTO.drawNumbers();
        this.date = msContestDTO.date();
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public MSPool getPool() {
        return pool;
    }

    public void setPool(MSPool pool) {
        this.pool = pool;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MSContest contest = (MSContest) o;
        return Objects.equals(id, contest.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
