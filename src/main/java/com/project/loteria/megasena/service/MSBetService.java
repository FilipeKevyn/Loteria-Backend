package com.project.loteria.megasena.service;

import com.project.loteria.megasena.entities.MSBet;
import com.project.loteria.megasena.repositories.MSBetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MSBetService{
    @Autowired
    private MSBetRepository betRepository;

    public MSBet findById(Long id){
        Optional<MSBet> bet = betRepository.findById(id);
        return bet.orElseThrow(() -> new RuntimeException()); // criar excesão personalizada
    }
    public MSBet insert(MSBet obj){
        System.out.println("       | INSERINDO BET |   betservice     ");
        return betRepository.save(obj);
    }

}
