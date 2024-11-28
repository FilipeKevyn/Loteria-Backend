package com.project.loteria.config;

import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Contest;
import com.project.loteria.repositories.BetRepository;
import com.project.loteria.service.BetService;
import com.project.loteria.service.ContestService;
import com.project.loteria.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Date;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private BetRepository betRepository;

    @Autowired
    private ContestService contestService;

    @Autowired
    private ResultService resultService;

    @Override
    public void run(String... args) throws Exception {
        String[] jogo = new String[] {"1","2","4"};
        Bet bet1 = new Bet(null, "312", jogo);

        resultService.insertBet(betRepository.save(bet1));


//        resultService.insertBet(bet1);
    }
}
