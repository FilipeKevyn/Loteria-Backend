package com.project.loteria.config;

import com.project.loteria.dao.repositories.BetDAOImpl;
import com.project.loteria.dao.repositories.PoolDAOImpl;
import com.project.loteria.entities.Pool;
import com.project.loteria.entities.Bet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.*;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private PoolDAOImpl poolrepository;

    @Autowired
    private BetDAOImpl betrepository;


    @Override
    public void run(String... args) throws Exception {
        Pool pool = new Pool("Filipe");
        pool.setId(UUID.randomUUID());
        poolrepository.insert(pool);
        System.out.println("Adicionou o pool");

        Bet bet = new Bet();
        bet.setId(UUID.randomUUID());
        bet.setValueInvested(100);
        bet.setPool(pool);
        betrepository.insert(bet);
        System.out.println("Adicionou a bet ao pool");

        System.out.println(poolrepository.findById(pool.getId().toString()).getBets().toString());
    }
}
