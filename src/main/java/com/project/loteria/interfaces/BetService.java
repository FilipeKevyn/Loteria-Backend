package com.project.loteria.interfaces;


import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Pool;

import java.util.List;


public interface BetService {
    Bet insert(Bet obj);
    void addBetToPool(Long poolId, Bet bet);
    void setMatched(Bet bet, int matched);
    List<Integer> sortBet(List<Integer> bet);
    boolean verifySameBet(Bet bet, Pool pool);
}
