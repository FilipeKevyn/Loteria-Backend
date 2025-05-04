package com.project.loteria.dao;

import com.project.loteria.entities.Bet;

import java.util.Set;

public interface BetDAO {
    Bet findById(String id);
    Set<Bet> findBetsByPool(String poolId);
    Bet insert(Bet bet);
    Bet save(Bet bet);
    void delete(String id);
}
