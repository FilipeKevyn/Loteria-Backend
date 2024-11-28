package com.project.loteria.service;

import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Contest;
import com.project.loteria.entities.Result;
import com.project.loteria.repositories.ResultRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private BetService betService;

    @Transactional
    public Result insertBet(Bet bet){
        Result result = new Result();
        result.setBet(bet);
        betService.insert(bet);
        System.out.println("       | INSERINDO BET |    resultservice    ");
        return resultRepository.save(result);
    }

    public List<Result> insertContest(Contest contest){
        List<Result> results = resultRepository.findAll();
        results.forEach(e -> e.setContest(contest));
        return resultRepository.saveAll(results);
    }
}
