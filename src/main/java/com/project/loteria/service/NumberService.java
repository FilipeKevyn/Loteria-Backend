package com.project.loteria.service;

import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Number;
import com.project.loteria.entities.Pool;
import com.project.loteria.repositories.NumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class NumberService {
    @Autowired
    private NumberRepository repository;

    public Number insert(Number betNumber){
        return repository.save(betNumber);
    }

    public void insertAll(Set<Number> numbers){
        repository.saveAll(numbers);
    }

    public Number findByNumberAndPool(int num, Pool pool){
        return repository.findByNumberAndPool(num, pool);
    }

    public Set<Number> insertNumbersInBet(Bet bet, Pool pool){
        Set<Number> numberSet = new HashSet<>();
        for (int i = 0; i < bet.getQuantityNumbers(); i++) {
            int num = bet.getBetNumbersArray().get(i);

            if (pool.getContest() != null || validateNumberExistInContest(pool.getContest().getNumbers(), num)){
                Number number = repository.findByNumberAndContest(num, pool.getContest());
                number.setMatched(true);
                numberSet.add(number);
            }
            else if (validateNumberExistInPool(pool.getNumbers(), num)){
                Number number = repository.findByNumberAndPool(num, pool);
                numberSet.add(number);
                System.out.println("Numero existente " + i);
            }
            else {
                Number number = new Number(pool, bet, num);
                numberSet.add(number);
                System.out.println("Criando numero...");
            }
        }

        System.out.println("Numeros inseridos\n");

        return numberSet;
    }

    public boolean validateNumberExistInPool(Set<Number> poolNumbers, int num){
        for (Number betNumber : poolNumbers) {
            if (betNumber.getNumber() == num) {
                return true;
            }
        }
        return false;
    }

    public boolean validateNumberExistInContest(Set<Number> contestNumbers, int num){
        for (Number betNumber : contestNumbers) {
            if (betNumber.getNumber() == num) {
                return true;
            }
        }
        return false;
    }
}
