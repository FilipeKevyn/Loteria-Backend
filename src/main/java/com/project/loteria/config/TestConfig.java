package com.project.loteria.config;

import com.project.loteria.megasena.entities.MSBet;
import com.project.loteria.megasena.service.MSBetService;
import com.project.loteria.megasena.service.MSContestService;
import com.project.loteria.megasena.service.MSPoolService;
import com.project.loteria.megasena.service.MSResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private MSContestService contestService;

    @Autowired
    private MSResultService resultService;

    @Autowired
    private MSBetService betService;

    @Autowired
    private MSPoolService service;
    @Override
    public void run(String... args) throws Exception {
        service.createPool();

        Integer[] jogo = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        MSBet bet1 = new MSBet(null, "312", jogo, 10);

        betService.addBetToPool(1L, bet1);
    }
}
