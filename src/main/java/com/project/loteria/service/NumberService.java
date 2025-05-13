package com.project.loteria.service;

import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Contest;
import com.project.loteria.entities.Number;
import com.project.loteria.entities.Pool;
import com.project.loteria.repositories.NumberRepository;
import com.project.loteria.service.resolver.ContestNumberResolver;
import com.project.loteria.service.resolver.NumberResolver;
import com.project.loteria.service.resolver.PoolNumberResolver;
import com.project.loteria.service.validator.ContestNumbersValidator;
import com.project.loteria.service.validator.NumberExistenceValidator;
import com.project.loteria.service.validator.PoolNumbersValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class NumberService {
    private static final Logger logger = LoggerFactory.getLogger(NumberService.class);
    @Autowired
    private NumberRepository repository;

    public Number insert(Number betNumber){
        return repository.save(betNumber);
    }

    public void insertAll(Set<Number> numbers){
        repository.saveAll(numbers);
    }

    public Number findByNumberAndPool(int num, Pool pool){
        return repository.findByNumberAndPool(num, pool);
    }

    public Set<Number> insertNumbersInBet(Bet bet, Pool pool){
        logger.info("Inserindo numeros na Bet");

        PoolNumbersValidator poolValidator = new PoolNumbersValidator(pool);
        ContestNumbersValidator contestValidator = new ContestNumbersValidator(pool.getContest());
        NumberResolver resolver = new PoolNumberResolver(pool, bet, repository, contestValidator, poolValidator);
        NumberInsertionService insertionService = new NumberInsertionService(resolver);

        return insertionService.insertNumbers(bet.getBetNumbersArray());
    }

    public Set<Number> insertNumbersInContest(Contest contest, Pool pool){
        logger.info("Inserindo numeros ao contest");

        PoolNumbersValidator poolNumbersValidator = new PoolNumbersValidator(pool);
        NumberResolver resolver = new ContestNumberResolver(pool, contest, repository, poolNumbersValidator);
        NumberInsertionService insertionService = new NumberInsertionService(resolver);

        return insertionService.insertNumbers(contest.getDrawnNumbers());
    }

}
