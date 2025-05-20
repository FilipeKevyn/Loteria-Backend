package com.project.loteria.dao;

import com.project.loteria.entities.Bet;

import java.util.Collection;
import java.util.Set;

public interface BetDAO extends DAO<Bet>{
    Set<Bet> findBetsByPool(String poolId);
    Set<Bet> saveAll(Set<Bet> bets);
}
