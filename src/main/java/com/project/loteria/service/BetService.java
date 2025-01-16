package com.project.loteria.service;

import com.project.loteria.exceptions.BetNotFoundException;
import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Pool;
import com.project.loteria.repositories.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BetService implements com.project.loteria.interfaces.BetService {
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
        return betRepository.save(obj);
    }

    public Page<Bet> findBetsByPool(Long poolId, Pageable pageable){
        Pool pool = poolService.findById(poolId);
        return betRepository.findByPool(pool, pageable);
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

}
