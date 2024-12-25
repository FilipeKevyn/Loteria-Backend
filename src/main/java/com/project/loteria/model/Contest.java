package com.project.loteria.model;

import java.util.Date;

public abstract class Contest {
    private Long id;
    private Integer[] drawNumbers;
    private String codeContest;

    public Contest(){
    }

    public Contest(Long id, Integer[] drawNumbers, String codeContest) {
        this.id = id;
        this.drawNumbers = drawNumbers;
        this.codeContest = codeContest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer[] getDrawNumbers() {
        return drawNumbers;
    }

    public String getCodeContest() {
        return codeContest;
    }

    public void setCodeContest(String codeContest) {
        this.codeContest = codeContest;
    }
}
