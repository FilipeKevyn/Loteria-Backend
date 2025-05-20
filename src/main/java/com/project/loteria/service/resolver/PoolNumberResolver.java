package com.project.loteria.service.resolver;

import com.project.loteria.dao.repositories.NumberDAOImpl;
import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Number;
import com.project.loteria.entities.Pool;
import com.project.loteria.service.validator.ContestNumbersValidator;
import com.project.loteria.service.validator.PoolNumbersValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PoolNumberResolver implements NumberResolver{
    private static final Logger logger = LoggerFactory.getLogger(PoolNumberResolver.class);

    private final Pool pool;
    private final Bet bet;
    private final NumberDAOImpl repository;
    private final ContestNumbersValidator contestValidator;
    private final PoolNumbersValidator poolValidator;

    public PoolNumberResolver(Pool pool,
                              Bet bet,
                              NumberDAOImpl repository,
                              ContestNumbersValidator contestValidator,
                              PoolNumbersValidator poolNumbersValidator) {
        this.pool = pool;
        this.bet = bet;
        this.repository = repository;
        this.contestValidator = contestValidator;
        this.poolValidator = poolNumbersValidator;
    }

    @Override
    public Number resolve(int num) {
        if (pool.getContest() != null && contestValidator.exists(num)){
            logger.debug("Número {} encontrado no contest.", num);
            Number number = repository.findByNumberAndContest(num, pool.getContest().getId());
            number.setMatched(true);
            return number;

        } else if (pool.getNumbers() != null && poolValidator.exists(num)) {
            logger.debug("Número {} encontrado no pool.", num);
            return repository.findByNumberAndPool(num, pool.getId());
        }

        return new Number(pool, bet, num);
    }
}
