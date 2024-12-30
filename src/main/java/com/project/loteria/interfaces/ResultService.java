package com.project.loteria.interfaces;

public interface ResultService {
    void verifyAllBets(Long poolId);
    int verifyMatched(Integer[] bet, Integer[] drawNumbers);

}
