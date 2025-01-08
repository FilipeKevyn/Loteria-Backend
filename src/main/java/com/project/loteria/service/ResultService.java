package com.project.loteria.service;

import com.project.loteria.interfaces.BetService;
import com.project.loteria.interfaces.PoolService;
import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Pool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ResultService implements com.project.loteria.interfaces.ResultService {
    @Autowired
    private PoolService poolService;

    private final BetService betService;

    @Autowired
    public ResultService(BetService betService) {
        this.betService = betService;
    }

    public void verifyAllBets(Long poolId){
        Pool pool = poolService.findById(poolId);
        List<Integer> drawnNumbers = pool.getContest().getDrawnNumbers();
        List<Bet> bets = poolService.getAllBets(pool);

        for (Bet bet : bets){
            if (bet.getMatched() == 0){
                int matched = verifyMatched(bet.getBet(), drawnNumbers);
                betService.setMatched(bet, matched);
            }
        }
    }

    public void verifyBet(Long poolId, Bet bet){
        List<Integer> drawNumbers = poolService.findById(poolId).getContest().getDrawnNumbers();
        int matched = verifyMatched(bet.getBet(), drawNumbers);
        betService.setMatched(bet, matched);
    }

    public int verifyMatched(List<Integer> bet, List<Integer> drawNumbers){
        int count = 0;

        for (Integer number : drawNumbers){
            if (bet.contains(number)){
                count++;
            }
        }

        return count;
    }
}
