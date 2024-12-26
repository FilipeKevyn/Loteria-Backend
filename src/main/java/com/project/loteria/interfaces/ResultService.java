package com.project.loteria.interfaces;

public interface ResultService<T> {
    T addResultToPool(Long id, T result);
    void verifyAllBets(Long poolId);
    int verifyMatched(Integer[] bet, Integer[] drawNumbers);

}
