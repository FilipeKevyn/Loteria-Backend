package com.project.loteria.service;

import com.project.loteria.entities.Contest;
import com.project.loteria.entities.Pool;
import com.project.loteria.repositories.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContestService implements com.project.loteria.interfaces.ContestService {
    @Autowired
    private ContestRepository contestRepository;

    @Autowired
    private PoolService msPoolService;

    public Contest findById(Long id){
        Optional<Contest> contest = contestRepository.findById(id);
        return contest.orElseThrow(() -> new RuntimeException()); // criar excessão personalizada
    }

    public Contest insert(Contest obj){
        return contestRepository.save(obj);
    }

    public void setContestInPool(Long id, Contest contest){
        Contest contestSaved = insert(contest);
        Pool pool = msPoolService.findById(id);
        pool.setContest(contestSaved);
        contestSaved.setPool(pool);
    }
}
