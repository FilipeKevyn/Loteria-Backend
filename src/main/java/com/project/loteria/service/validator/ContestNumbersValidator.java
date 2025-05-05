package com.project.loteria.service.validator;

import com.project.loteria.entities.Contest;
import com.project.loteria.entities.Pool;
import org.springframework.transaction.annotation.Transactional;

public class ContestNumbersValidator implements NumberExistenceValidator{

    private final Contest contest;

    public ContestNumbersValidator(Contest contest) {
        this.contest = contest;
    }

    @Override
    public boolean exists(int number) {
        return contest.getNumbers().stream().anyMatch(e -> e.getNumber() == number);
    }
}
