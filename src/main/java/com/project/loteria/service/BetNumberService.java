package com.project.loteria.service;

import com.project.loteria.entities.Bet;
import com.project.loteria.entities.BetNumber;
import com.project.loteria.repositories.BetNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BetNumberService {
    @Autowired
    private BetNumberRepository repository;

    public BetNumber insertNumber(Bet bet, int num){
        // validar

        BetNumber number = new BetNumber(num, bet);
        return repository.save(number);
    }
}
