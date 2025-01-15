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
    private PoolService poolService;

    public Contest findById(Long id){
        Optional<Contest> contest = contestRepository.findById(id);
        return contest.orElseThrow(() -> new RuntimeException()); // criar excessão personalizada
    }

    public Contest insert(Contest obj){
        return contestRepository.save(obj);
    }

    public void setContestInPool(Long id, Contest contest){
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
