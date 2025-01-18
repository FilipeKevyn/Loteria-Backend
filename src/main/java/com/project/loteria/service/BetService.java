package com.project.loteria.service;

import com.project.loteria.exceptions.BetAlreadyExistsException;
import com.project.loteria.exceptions.BetNotFoundException;
import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Pool;
import com.project.loteria.repositories.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Bet betSaved = prepareBet(bet, pool);
        if (pool.getContest() != null) {
            resultService.verifyBet(poolId, betSaved);
        }
        poolService.addBetToPool(pool, betSaved);
    }

    public boolean verifySameBet(Bet bet, Pool pool){
        for (Bet bet1 : pool.getBets()){
            if (bet1.getBet().equals(bet.getBet())) {
                return true;
            }
        }
        return false;
    }

    public void setMatched(Bet bet, int matched){
        bet.setMatched(matched);
        betRepository.save(bet);
    }

    public Bet prepareBet(Bet bet, Pool pool){
        bet.setBet(sortBet(bet.getBet()));
        if (verifySameBet(bet, pool)) {
            throw new BetAlreadyExistsException();
        }
        bet.setPool(pool);
        return insert(bet);
    }

    public List<Integer> sortBet(List<Integer> bet) {
        List<Integer> betSorted = bet.stream().sorted().collect(Collectors.toList());
        return betSorted;
    }

}
