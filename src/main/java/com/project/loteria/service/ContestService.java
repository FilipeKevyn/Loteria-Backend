package com.project.loteria.service;

import com.project.loteria.entities.Contest;
import com.project.loteria.entities.Pool;
import com.project.loteria.repositories.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContestService {
    @Autowired
    private ContestRepository contestRepository;

    @Autowired
    private PoolService poolService;

    @Autowired
    private NumberService numberService;

    @Autowired
    private ResultService resultService;

    public void insertContestInPool(UUID poolId, Contest contest){
        Pool pool = poolService.findById(poolId);
        if (pool.getContest() != null){
            removeContestInPool(pool);
            resultService.updateContest(pool, contest);
            saveContest(contest, pool);
        }
        else {
            saveContest(contest, pool);
            resultService.verifyBetNumbers(pool);
        }
    }

    public void removeContestInPool(Pool pool){
        Contest contest = pool.getContest();
        pool.setContest(null);
        poolService.update(pool);

        contestRepository.delete(contest);
    }

    public void saveContest(Contest contest, Pool pool) {
        pool.setContest(contest);
        contest.setPool(pool);
        contest.setNumbers(numberService.insertNumbersInContest(contest, pool));
        contestRepository.save(contest);
        poolService.update(pool);
    }
}
