package com.project.loteria.service;

import com.project.loteria.entities.Number;
import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Pool;
import com.project.loteria.exceptions.BetAlreadyExistsException;
import com.project.loteria.exceptions.BetNotFoundException;
import com.project.loteria.interfaces.GameTypeStrategy;
import com.project.loteria.repositories.BetRepository;
import com.project.loteria.service.strategy.LotofacilStrategy;
import com.project.loteria.service.strategy.MegaSenaStrategy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BetService {
    @Autowired
    private BetRepository betRepository;

    @Autowired
    private PoolService poolService;

    @Autowired
    private NumberService betNumberService;
    @Autowired
    @Lazy
    private ResultService resultService;

    private Map<String, GameTypeStrategy> strategyType = Map.of("Mega-Sena", new MegaSenaStrategy(),
            "Lotofácil", new LotofacilStrategy());

    public Bet insert(Bet obj){
        return betRepository.save(obj);
    }

    public Bet findById(UUID id){
        return betRepository.findById(id).orElseThrow(() -> new BetNotFoundException());
    }

    public Page<Bet> findBetsByPool(Long poolId, Pageable pageable){
        Pool pool = poolService.findById(poolId);
        return betRepository.findByPool(pool, pageable);
    }

    @Transactional
    public void addBetToPool(Long poolId, Bet bet){
        Pool pool = poolService.findById(poolId);
        Bet betSaved = prepareBet(bet, pool);

        poolService.addBetToPool(pool, betSaved);

        Integer mostRepeated = betRepository.findMostRepeatedNumber(poolId);
        pool.setMostRepeatedNumber(mostRepeated);
        poolService.update(pool);
    }

    public Bet prepareBet(Bet bet, Pool pool){
        Set<Number> betNumbers = betNumberService.insertNumbersInBet(bet, pool);
        bet.setBetNumbers(betNumbers);

        if (verifySameBet(bet, pool)){
            throw new BetAlreadyExistsException();
        }
        setValueInvested(bet);
        bet.setPool(pool);
        insert(bet);

        betNumberService.insertAll(betNumbers);


        return bet;
    }

    public boolean verifySameBet(Bet bet, Pool pool){
        for (Bet betPool : pool.getBets()) {
            if (bet.equals(betPool)){
                return true;
            }
        }
        System.out.println("Bets não iguais");
        return false;
    }

    public void setMatched(Bet bet, int matched){
        bet.setMatched(matched);
        betRepository.save(bet);
    }

    public int countMatched(Bet bet){
        return betRepository.countMatchedNumbersByBet(bet);
    }

    public void setValueInvested(Bet bet){
        double valueInvested = strategyType.get(bet.getGameType())
                .calculateValueInvested(bet.getQuantityNumbers());
        bet.setValueInvested(valueInvested);
    }

    public List<Integer> sortBet(List<Integer> bet) {
         return bet.stream().sorted().toList();
    }

    public void delete(UUID id){
        Bet bet = findById(id);
        Pool pool = bet.getPool();
        poolService.subtractValueTotal(pool, bet);
        betRepository.delete(bet);
    }

}
