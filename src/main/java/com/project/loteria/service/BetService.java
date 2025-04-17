package com.project.loteria.service;

import com.project.loteria.entities.BetNumber;
import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Pool;
import com.project.loteria.exceptions.BetAlreadyExistsException;
import com.project.loteria.exceptions.BetNotFoundException;
import com.project.loteria.interfaces.GameTypeStrategy;
import com.project.loteria.repositories.BetRepository;
import com.project.loteria.service.strategy.LotofacilStrategy;
import com.project.loteria.service.strategy.MegaSenaStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class BetService {
    @Autowired
    private BetRepository betRepository;

    @Autowired
    private PoolService poolService;

    @Autowired
    private BetNumberService betNumberService;
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

    public Page<Bet> findBetsByPool(UUID poolId, Pageable pageable){
        Pool pool = poolService.findById(poolId);
        return betRepository.findByPool(pool, pageable);
    }

    public Bet prepareBet(Bet bet, Pool pool){
        if (verifySameBet(bet, pool)){
            throw new BetAlreadyExistsException();
        }
        bet.setBetNumbersArray(sortBet(bet.getBetNumbersArray()));
        setValueInvested(bet);
        bet.setPool(pool);

        return insert(bet);
    }

    public void insertNumbers(Bet bet, Pool pool){
        for (int i = 0; i < bet.getQuantityNumbers(); i++) {
            BetNumber betNumber = betNumberService.insertNumber(bet, pool, bet.getBetNumbersArray().get(i));
            bet.getBetNumbers().add(betNumber);
        }
    }

    public void addBetToPool(UUID poolId, Bet bet){
        Pool pool = poolService.findById(poolId);
        Bet betSaved = prepareBet(bet, pool);
        if (pool.getContest() != null) {
            resultService.verifyBet(poolId, betSaved);
        }
        insertNumbers(bet, pool);
        poolService.addBetToPool(pool, betSaved);
    }

    public boolean verifySameBet(Bet bet, Pool pool){
        for (Bet bet1 : pool.getBets()){
            if (bet1.getBetNumbersArray().equals(bet.getBetNumbersArray())) {
                return true;
            }
        }
        return false;
    }

    public void setMatched(Bet bet, int matched){
        bet.setMatched(matched);
        betRepository.save(bet);
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
