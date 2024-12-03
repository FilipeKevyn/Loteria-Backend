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
    private String[] drawnNumbers;
    private Date date;

    @JsonIgnore
    @OneToMany(mappedBy = "contest", fetch = FetchType.LAZY)
    private Set<MSResult> results = new HashSet<>();

    public MSContest(){}

    public MSContest(Long id, String[] drawnNumbers, Date date) {
        this.id = id;
        this.drawnNumbers = drawnNumbers;
        this.date = date;
    }

    public MSContest(MSContestDTO msContestDTO){
        this.id = msContestDTO.id();
        this.drawnNumbers = msContestDTO.drawNumbers();
        this.date = msContestDTO.date();
    }

    public void addResult(MSResult result){
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

    public Set<MSResult> getResults() {
        return results;
    }

    public void setResults(Set<MSResult> results) {
        this.results = results;
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
