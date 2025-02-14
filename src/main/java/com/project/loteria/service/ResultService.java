package com.project.loteria.service;

import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Pool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
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
            int matched = verifyMatched(bet.getBetNumbers(), drawnNumbers);
            betService.setMatched(bet, matched);
        }
    }

    public void verifyBet(Long poolId, Bet bet){
        List<Integer> drawNumbers = poolService.findById(poolId).getContest().getDrawnNumbers();
        int matched = verifyMatched(bet.getBetNumbers(), drawNumbers);
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
