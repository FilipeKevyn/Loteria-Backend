package com.project.loteria.service;

import com.project.loteria.entities.Bet;
import com.project.loteria.repositories.BetRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.Optional;

@Service
public class BetService {
    @Autowired
    private BetRepository betRepository;

    public Bet findById(Long id){
        Optional<Bet> bet = betRepository.findById(id);
        return bet.orElseThrow(() -> new RuntimeException()); // criar excesão personalizada
    }
    public Bet insert(Bet obj){
        System.out.println("       | INSERINDO BET |   betservice     ");
        return betRepository.save(obj);
    }
}
