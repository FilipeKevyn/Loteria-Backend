package com.project.loteria.config;

import com.project.loteria.entities.Contest;
import com.project.loteria.entities.Pool;
import com.project.loteria.entities.Bet;
import com.project.loteria.entities.User;
import com.project.loteria.repositories.BetRepository;
import com.project.loteria.repositories.ContestRepository;
import com.project.loteria.repositories.PoolRepository;
import com.project.loteria.repositories.UserRepository;
import com.project.loteria.service.BetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.lang.reflect.Array;
import java.util.*;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private PoolRepository poolRepository;

    @Autowired
    private BetRepository betRepository;

    @Autowired
    private ContestRepository contestRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {
//        Pool pool = new Pool("Teste");
//        poolRepository.insert(pool);

        List<Integer> numbers = new ArrayList<>(Arrays.asList(1,2,3,4));
        Bet bet = new Bet(numbers, "Mega-Sena");
        betRepository.insert(bet);

    }
}
