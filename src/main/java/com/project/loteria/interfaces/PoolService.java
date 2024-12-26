package com.project.loteria.interfaces;

import java.util.List;

public interface PoolService<T, B> {
    T createPool();
    T findById(Long id);
    List<B> getAllBets(T pool);
    void addBetToPool(T pool, B bet);
    void getValueTotal(Long id);
}
