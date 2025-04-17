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
        // validar

        BetNumber number = new BetNumber(num, bet);
        return repository.save(number);
    }

    public boolean validateNumberExist(Pool pool, int num){
        Set<BetNumber> numbers = pool.getBetNumbers();
        for (BetNumber betNumber : numbers) {
            if (betNumber.getNumber() == num) {
                return true;
            }
        }
        return false;
    }
}
