package com.project.loteria.service;

import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Contest;
import com.project.loteria.entities.Result;
import com.project.loteria.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
    @Autowired
    private ResultRepository resultRepository;

    public Result insertBet(Bet bet){
        Result result = new Result();
        result.setBet(bet);
        return resultRepository.save(result);
    }

    public List<Result> insertContest(Contest contest){
        List<Result> results = resultRepository.findAll();
        results.forEach(e -> e.setContest(contest));
        return resultRepository.saveAll(results);
    }
}
