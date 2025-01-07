package com.project.loteria.interfaces;

import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Pool;

import java.util.List;

public interface PoolService {
    Pool createPool(String name);
    Pool findById(Long id);
    List<Bet> getAllBets(Pool pool);
    void addBetToPool(Pool pool, Bet bet);
    void setValueTotal(Pool pool, Bet bet);
}
