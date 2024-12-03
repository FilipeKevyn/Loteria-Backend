package com.project.loteria.config;

import com.project.loteria.megasena.entities.MSBet;
import com.project.loteria.megasena.repositories.MSBetRepository;
import com.project.loteria.megasena.service.MSContestService;
import com.project.loteria.megasena.service.MSResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private MSBetRepository betRepository;

    @Autowired
    private MSContestService contestService;

    @Autowired
    private MSResultService resultService;

    @Override
    public void run(String... args) throws Exception {
        String[] jogo = new String[] {"1","2","4"};
        MSBet bet1 = new MSBet(null, "312", jogo);

        resultService.insertBet(betRepository.save(bet1));


//        resultService.insertBet(bet1);
    }
}
