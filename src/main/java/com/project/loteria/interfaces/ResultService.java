package com.project.loteria.interfaces;

import com.project.loteria.entities.Bet;

import java.util.List;

public interface ResultService {
    void verifyBet(Long poolId, Bet bet);
    void verifyAllBets(Long poolId);
    int verifyMatched(List<Integer> bet, List<Integer> drawNumbers);

}
