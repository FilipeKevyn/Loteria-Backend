package com.project.loteria.config;

import com.project.loteria.megasena.entities.MSBet;
import com.project.loteria.megasena.repositories.MSBetRepository;
import com.project.loteria.megasena.service.MSBetService;
import com.project.loteria.megasena.service.MSContestService;
import com.project.loteria.megasena.service.MSResultService;
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
    private MSContestService contestService;

    @Autowired
    private MSResultService resultService;



    @Override
    public void run(String... args) throws Exception {
        Integer[] jogo = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        MSBet bet1 = new MSBet(null, "312", jogo, 10);

        resultService.insertBet(bet1);


//        resultService.insertBet(bet1);
    }
}
