package com.project.loteria.megasena.service;

import com.project.loteria.megasena.entities.MSBet;
import com.project.loteria.megasena.entities.MSContest;
import com.project.loteria.megasena.entities.MSResult;
import com.project.loteria.megasena.repositories.MSResultRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MSResultService {
    @Autowired
    private MSResultRepository resultRepository;

    @Autowired
    private MSBetService betService;

    @Transactional
    public MSResult insertBet(MSBet bet){
        MSResult result = new MSResult();
        result.setBet(bet);
        betService.insert(bet);
        System.out.println("       | INSERINDO BET |    resultservice    ");
        return resultRepository.save(result);
    }

    public List<MSResult> insertContest(MSContest contest){
        List<MSResult> results = resultRepository.findAll();
        results.forEach(e -> e.setContest(contest));
        return resultRepository.saveAll(results);
    }
}
