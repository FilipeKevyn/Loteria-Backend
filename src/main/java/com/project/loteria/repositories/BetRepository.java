package com.project.loteria.repositories;

import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Pool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BetRepository extends MongoRepository<Bet, String> {
    //Page<Bet> findByPool(Pool pool, Pageable pageable);
}
