package com.project.loteria.config;

import com.project.loteria.dao.repositories.BetDAOImpl;
import com.project.loteria.dao.repositories.PoolDAOImpl;
import com.project.loteria.entities.Pool;
import com.project.loteria.entities.Bet;
import com.project.loteria.entities.BetNumber;
import com.project.loteria.entities.User;
import com.project.loteria.service.BetService;

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

        Bet bet = new Bet();
        bet.setId(UUID.randomUUID());
        bet.setValueInvested(100);
        bet.setPool(pool);
        betrepository.insert(bet);

        pool.setValueTotal(100);
        poolrepository.save(pool);

        bet.setMatched(2);
        betrepository.save(bet);

    }
}
