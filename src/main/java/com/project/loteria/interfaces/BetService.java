package com.project.loteria.interfaces;


import com.project.loteria.entities.Bet;


public interface BetService {
    Bet insert(Bet obj);
    void addBetToPool(Long poolId, Bet bet);
    void setMatched(Bet bet, int matched);
}
