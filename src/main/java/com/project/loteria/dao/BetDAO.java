package com.project.loteria.dao;

import com.project.loteria.entities.Bet;

import java.util.Collection;
import java.util.Set;

public interface BetDAO {
    Bet findById(String id);
    Set<Bet> findBetsByPool(String poolId);
    Bet insert(Bet bet);
    Bet save(Bet bet);
    Set<Bet> saveAll(Set<Bet> bets);
    void delete(String id);
}
