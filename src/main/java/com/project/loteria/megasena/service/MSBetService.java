package com.project.loteria.megasena.service;

import com.project.loteria.exceptions.BetNotFoundException;
import com.project.loteria.interfaces.BetService;
import com.project.loteria.megasena.entities.MSBet;
import com.project.loteria.megasena.entities.MSPool;
import com.project.loteria.megasena.repositories.MSBetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MSBetService {
    @Autowired
    private MSBetRepository betRepository;

    @Autowired
    private MSPoolService msPoolService;

    @Autowired
    @Lazy // gambiarra grande
    private MSResultService resultService;

    public MSBet findById(Long id){
        Optional<MSBet> bet = betRepository.findById(id);
        return bet.orElseThrow(() -> new BetNotFoundException(id)); // criar excesão personalizada
    }
    public MSBet insert(MSBet obj){
        validate(obj.getBet());
        return betRepository.save(obj);
    }

    public void addBetToPool(Long poolId, MSBet bet){
        MSPool pool = msPoolService.findById(poolId);
        bet.setPool(pool);
        MSBet betSaved = insert(bet);
        if (pool.getContest() != null) {
            resultService.verifyBet(poolId, betSaved);
        }
        msPoolService.addBetToPool(pool, betSaved);
    }

    public void setMatched(MSBet bet, int matched){
        bet.setMatched(matched);
        betRepository.save(bet);
    }

    public void validate(Integer[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < 0 || numbers[i] > 60){
                throw new IllegalArgumentException("Number INVALID: " + numbers[i]);
            }
        }
    }

}
