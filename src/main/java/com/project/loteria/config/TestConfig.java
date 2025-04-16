package com.project.loteria.config;

import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Pool;
import com.project.loteria.repositories.PoolRepository;
import com.project.loteria.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private BetService service;

    @Autowired
    private PoolRepository poolRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        Pool pool = new Pool();
        pool.setTitle("Meu Bolão"); // Atribua algo ao título, pois ele pode ser obrigatório
        pool.setType("Mega-Sena");

        poolRepository.save(pool); // <-- Salve o pool primeiro

        Bet bet = new Bet();
        bet.setPool(pool);
        bet.setBetNumbersArray(list);
        bet.setQuantityNumbers(list.size());
        bet.setType("Mega-Sena");

        pool.getBets().add(bet); // opcional, mas ajuda a manter a consistência da entidade


    }
}
