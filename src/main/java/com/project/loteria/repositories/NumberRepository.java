package com.project.loteria.repositories;

import com.project.loteria.entities.Contest;
import com.project.loteria.entities.Number;
import com.project.loteria.entities.Pool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NumberRepository extends JpaRepository<Number, UUID> {
    Number findByNumberAndPool(int num, Pool pool);
    Number findByNumberAndContest(int num, Contest contest);
}
