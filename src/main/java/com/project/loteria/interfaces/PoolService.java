package com.project.loteria.interfaces;

import java.util.List;

public interface PoolService<T, B, R> {
    T createPool();
    T findById(Long id);
    List<B> getAllBets(T pool);
    void addBetToPool(T pool, B bet);
    void addResultToPool(T pool, R result);
    void getValueTotal(Long id);
}
