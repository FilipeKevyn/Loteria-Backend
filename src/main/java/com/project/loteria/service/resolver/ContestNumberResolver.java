package com.project.loteria.service.resolver;

import com.project.loteria.entities.Contest;
import com.project.loteria.entities.Number;
import com.project.loteria.entities.Pool;
import com.project.loteria.repositories.NumberRepository;
import com.project.loteria.service.validator.ContestNumbersValidator;
import com.project.loteria.service.validator.PoolNumbersValidator;

public class ContestNumberResolver implements NumberResolver{

    private final Pool pool;
    private final Contest contest;
    private final NumberRepository repository;
    private final PoolNumbersValidator poolValidator;

    public ContestNumberResolver(Pool pool,
                                 Contest contest,
                                 NumberRepository repository,
                                 PoolNumbersValidator poolNumbersValidator) {
        this.pool = pool;
        this.contest = contest;
        this.repository = repository;
        this.poolValidator = poolNumbersValidator;
    }

    @Override
    public Number resolve(int num) {
        if (poolValidator.exists(num)){
//            return repository.findByNumberAndPool(num, pool);
        }
        return new Number(pool, contest, num);
    }
}
