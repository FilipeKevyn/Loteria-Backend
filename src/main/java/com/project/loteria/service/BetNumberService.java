package com.project.loteria.service;

import com.project.loteria.entities.Bet;
import com.project.loteria.entities.BetNumber;
import com.project.loteria.entities.Pool;
import com.project.loteria.repositories.BetNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BetNumberService {
    @Autowired
    private BetNumberRepository repository;

    public BetNumber insertNumber(Bet bet, Pool pool, int num){
        if (validateNumberExist(pool.getBetNumbers(), num)){
            BetNumber number = repository.findByNumberAndPool(num, pool);
            return repository.save(number);
        }

        BetNumber number = new BetNumber(pool, bet, num);
        return repository.save(number);
    }

    public boolean validateNumberExist(Set<BetNumber> poolBetNumbers, int num){
        for (BetNumber betNumber : poolBetNumbers) {
            if (betNumber.getNumber() == num) {
                return true;
            }
        }
        return false;
    }
}
