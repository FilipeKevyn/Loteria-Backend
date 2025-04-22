package com.project.loteria.repositories;

import com.project.loteria.entities.Bet;
import com.project.loteria.entities.BetNumber;
import com.project.loteria.entities.Pool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface BetNumberRepository extends JpaRepository<BetNumber, UUID> {
    BetNumber findByNumberAndPool(int num, Pool pool);
}
