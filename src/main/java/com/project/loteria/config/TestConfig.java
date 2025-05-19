package com.project.loteria.config;

import com.project.loteria.entities.Pool;
import com.project.loteria.entities.Bet;
import com.project.loteria.repositories.PoolRepository;
import com.project.loteria.repositories.UserRepository;
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
    private UserRepository repository;
    @Autowired
    private BetService service;

    @Autowired
    private PoolRepository poolRepository;


    @Override
    public void run(String... args) throws Exception {
//        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        Pool pool = new Pool();
        pool.setTitle("Meu Bolão"); // Atribua algo ao título, pois ele pode ser obrigatório
        pool.setType("Mega-Sena");
        poolRepository.save(pool); // <-- Salve o pool primeiro

//        Bet bet = new Bet();
//        bet.setPool(pool);
//        bet.setType("Mega-Sena");
//        bet.setBetNumbersArray(List.of(1,3,5,7));
//
//        service.addBetToPool(pool.getId(), bet);
//
//
//        Bet bet2 = new Bet();
//        bet2.setPool(pool);
//        bet2.setType("Mega-Sena");
//        bet2.setBetNumbersArray(List.of(2,4,6,8));
//
//        service.addBetToPool(pool.getId(), bet2);
//
//        pool.getBets().add(bet); // opcional, mas ajuda a manter a consistência da entidade
    }
}
