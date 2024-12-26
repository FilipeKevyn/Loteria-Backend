package com.project.loteria.megasena.service;

import com.project.loteria.interfaces.ResultService;
import com.project.loteria.megasena.entities.MSBet;
import com.project.loteria.megasena.entities.MSPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MSResultService{

    @Autowired
    private MSPoolService poolService;

    @Autowired
    private MSBetService betService;

    public void verifyAllBets(Long poolId){
        MSPool pool = poolService.findById(poolId);
        Integer[] drawnNumbers = pool.getContest().getDrawnNumbers();
        List<MSBet> bets = poolService.getAllBets(pool);

        for (MSBet bet : bets){
            if (bet.getMatched() == 0){
                int matched = verifyMatched(bet.getBet(), drawnNumbers);
                betService.setMatched(bet, matched);
            }
        }
    }

    public void verifyBet(Long poolId, MSBet bet){
        Integer[] drawNumbers = poolService.findById(poolId).getContest().getDrawnNumbers();
        int matched = verifyMatched(bet.getBet(), drawNumbers);
        betService.setMatched(bet, matched);
    }

    public int verifyMatched(Integer[] bet, Integer[] drawNumbers){
        int count = 0;

        for (Integer number : drawNumbers){
            if (Arrays.asList(bet).contains(number)){
                count++;
            }
        }

        return count;
    }
}
