package com.project.loteria.service;

import com.project.loteria.entities.Contest;
import com.project.loteria.entities.Pool;
import com.project.loteria.repositories.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContestService {
    @Autowired
    private ContestRepository contestRepository;

    @Autowired
    private PoolService poolService;

    public Contest findById(String id){
        Optional<Contest> contest = contestRepository.findById(id);
        return contest.orElseThrow(() -> new RuntimeException()); // criar excessão personalizada
    }

    public Contest insert(Contest obj){
        return contestRepository.insert(obj);
    }

    public void setContestInPool(String id, Contest contest){
        Pool pool = poolService.findById(id);
        if (pool.getContest() != null){
            updateContest(pool);
        }
        pool.setContest(contest);
        contest.setPool(pool);
        contestRepository.save(contest);
        poolService.update(pool);
    }

    public void updateContest(Pool pool){
        Contest contest = pool.getContest();
        pool.setContest(null);
        poolService.update(pool);

        contestRepository.delete(contest);
    }
}
