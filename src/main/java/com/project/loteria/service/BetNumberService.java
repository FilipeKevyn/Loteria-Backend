package com.project.loteria.service;

import com.project.loteria.entities.Bet;
import com.project.loteria.entities.BetNumber;
import com.project.loteria.entities.Pool;
import com.project.loteria.repositories.BetNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BetNumberService {
    @Autowired
    private BetNumberRepository repository;

    public BetNumber insert(BetNumber betNumber){
        return repository.save(betNumber);
    }

    public void insertAll(Set<BetNumber> numbers){
        repository.saveAll(numbers);
    }

    public BetNumber findByNumberAndPool(int num, Pool pool){
        return repository.findByNumberAndPool(num, pool);
    }

    public Set<BetNumber> insertNumbers(Bet bet, Pool pool){
        Set<BetNumber> numberSet = new HashSet<>();
        for (int i = 0; i < bet.getQuantityNumbers(); i++) {
            int num = bet.getBetNumbersArray().get(i);

            if (validateNumberExist(pool.getBetNumbers(), num)){
                BetNumber number = repository.findByNumberAndPool(num, pool);
                numberSet.add(number);
                System.out.println("Numero existente " + i);
            }
            else {
                BetNumber betNumber = new BetNumber(pool, bet, num);
                numberSet.add(betNumber);
                System.out.println("Criando numero...");
            }
        }

        System.out.println("Numeros inseridos\n");

        return numberSet;
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
