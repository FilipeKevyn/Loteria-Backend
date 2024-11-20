package com.project.loteria.config;

import com.project.loteria.entities.Bet;
import com.project.loteria.repositories.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private BetRepository betRepository;
    @Override
    public void run(String... args) throws Exception {
        String[] jogo = new String[] {"1","2","4"};
        Bet bet1 = new Bet(null, "312", jogo);

        betRepository.save(bet1);
    }
}
