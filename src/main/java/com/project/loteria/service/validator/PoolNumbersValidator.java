package com.project.loteria.service.validator;

import com.project.loteria.entities.Pool;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public class PoolNumbersValidator implements NumberExistenceValidator{

    private final Pool pool;

    public PoolNumbersValidator(Pool pool) {
        this.pool = pool;
    }

    @Override
    public boolean exists(int number) {
        return pool.getNumbers() != null &&
                pool.getNumbers()
                        .stream()
                        .anyMatch(n -> n.getNumber() == number);
    }
}
