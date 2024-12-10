package com.project.loteria.megasena.service;

import com.project.loteria.interfaces.ResultService;
import com.project.loteria.megasena.entities.MSBet;
import com.project.loteria.megasena.entities.MSPool;
import com.project.loteria.megasena.entities.MSResult;
import com.project.loteria.megasena.repositories.MSResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MSResultService implements ResultService<MSResult> {
    @Autowired
    private MSResultRepository resultRepository;

    @Autowired
    private MSPoolService poolService;

    @Autowired
    private MSBetService betService;

    public MSResult creat(int matched){
        MSResult result = new MSResult(null, matched);
        return resultRepository.save(result);
    }

    public MSResult addResultToPool(Long poolId, MSResult result){
        MSPool pool = poolService.findById(poolId);
        poolService.addResultToPool(pool, result);
        return result;
    }

    public void verifyAllBets(Long poolId){
        MSPool pool = poolService.findById(poolId);
        Integer[] drawnNumbers = pool.getContest().getDrawnNumbers();
        List<MSBet> bets = poolService.getAllBets(pool);

        for (MSBet bet : bets){
            if (bet.getResult() == null){
                MSResult result = creat(verifyMatched(bet.getBet(), drawnNumbers));
                betService.setResult(bet, result);
                addResultToPool(poolId, result);
            }
        }
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
