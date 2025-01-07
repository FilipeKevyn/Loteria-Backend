package com.project.loteria.config;

import com.project.loteria.entities.Bet;
import com.project.loteria.repositories.BetRepository;
import com.project.loteria.megasena.service.MSBetService;
import com.project.loteria.service.ContestService;
import com.project.loteria.service.PoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private ContestService contestService;

    @Autowired
    private MSBetService betService;

    @Autowired
    BetRepository betRepository;

    @Autowired
    private PoolService poolService;
    @Override
    public void run(String... args) throws Exception {
//        MSPoolDTO dto = new MSPoolDTO("oi");
//        poolService.createPool(dto);

//        Integer[] jogo = new Integer[]{1,2,3,4,5,6,7,8,9,10};
//        Bet bet1 = new Bet(null, "312", jogo, 10);
//
//        betService.addBetToPool(1L, bet1);
    }
}
