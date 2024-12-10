package com.project.loteria.interfaces;



public interface BetService<B, R> {
    B insert(B obj);
    B addBetToPool(Long poolId, B bet);
    void setResult(B bet, R result);
    void validate(Integer[] numbers);
}
