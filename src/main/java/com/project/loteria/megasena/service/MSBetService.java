package com.project.loteria.megasena.service;

import com.project.loteria.exceptions.BetNotFoundException;
import com.project.loteria.interfaces.BetService;
import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Pool;
import com.project.loteria.repositories.BetRepository;
import com.project.loteria.service.PoolService;
import com.project.loteria.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MSBetService implements BetService{
    @Autowired
    private BetRepository betRepository;

    @Autowired
    private PoolService poolService;

    @Autowired
    @Lazy
    private ResultService resultService;

    public Bet findById(Long id){
        Optional<Bet> bet = betRepository.findById(id);
        return bet.orElseThrow(() -> new BetNotFoundException(id));
    }
    public Bet insert(Bet obj){
        validate(obj.getBet());
        return betRepository.save(obj);
    }

    public void addBetToPool(Long poolId, Bet bet){
        Pool pool = poolService.findById(poolId);
        bet.setPool(pool);
        Bet betSaved = insert(bet);
        if (pool.getContest() != null) {
            resultService.verifyBet(poolId, betSaved);
        }
        poolService.addBetToPool(pool, betSaved);
    }

    public void setMatched(Bet bet, int matched){
        bet.setMatched(matched);
        betRepository.save(bet);
    }

    public void validate(List<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) < 0 || numbers.get(i) > 60){
                throw new IllegalArgumentException("Number INVALID: " + numbers.get(i));
            }
        }
    }

}
