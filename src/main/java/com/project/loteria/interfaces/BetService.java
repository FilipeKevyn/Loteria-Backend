package com.project.loteria.interfaces;



public interface BetService<B> {
    B insert(B obj);
    void addBetToPool(Long poolId, B bet);
    void validate(Integer[] numbers);
}
