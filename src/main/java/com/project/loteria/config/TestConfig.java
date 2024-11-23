package com.project.loteria.config;

import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Contest;
import com.project.loteria.service.BetService;
import com.project.loteria.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Date;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private BetService betService;

    @Autowired
    private ContestService contestService;

    @Override
    public void run(String... args) throws Exception {
        String[] jogo = new String[] {"1","2","4"};
        Bet bet1 = new Bet(null, "312", jogo);

        betService.insert(bet1);

        String[] drawNumber = new String[] {"1","2","3"};
        Contest contest = new Contest(null, drawNumber, new Date());

        contestService.insert(contest);
    }
}
