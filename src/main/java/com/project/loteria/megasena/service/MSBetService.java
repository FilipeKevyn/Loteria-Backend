package com.project.loteria.megasena.service;

import com.project.loteria.exceptions.ValidateException;
import com.project.loteria.interfaces.BetService;
import com.project.loteria.megasena.entities.MSBet;
import com.project.loteria.megasena.entities.MSContest;
import com.project.loteria.megasena.entities.MSPool;
import com.project.loteria.megasena.entities.MSResult;
import com.project.loteria.megasena.repositories.MSBetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MSBetService implements BetService<MSBet> {
    @Autowired
    private MSBetRepository betRepository;

    @Autowired
    private MSPoolService msPoolService;

    public MSBet findById(Long id){
        Optional<MSBet> bet = betRepository.findById(id);
        return bet.orElseThrow(() -> new RuntimeException()); // criar excesão personalizada
    }
    public MSBet insert(MSBet obj){
        validate(obj.getBet());
        return betRepository.save(obj);
    }

    public void addBetToPool(Long poolId, MSBet bet){
        MSPool pool = msPoolService.findById(poolId);
        bet.setPool(pool);
        MSBet betSaved = insert(bet);
        msPoolService.addBetToPool(pool, betSaved);
    }

    public void setResult(MSBet bet, MSResult result){
        bet.setResult(result);
        betRepository.save(bet);
    }

    public void validate(Integer[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < 0 || numbers[i] > 60){
                throw new ValidateException("Number INVALID: " + numbers[i]);
            }
        }
    }

}
