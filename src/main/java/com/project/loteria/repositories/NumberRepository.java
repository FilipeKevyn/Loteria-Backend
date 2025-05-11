package com.project.loteria.repositories;

import com.project.loteria.entities.Contest;
import com.project.loteria.entities.Pool;

import java.util.UUID;

public interface NumberRepository {
    Number findByNumberAndPool(int num, Pool pool);
    Number findByNumberAndContest(int num, Contest contest);
}
