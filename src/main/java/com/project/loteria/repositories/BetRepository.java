package com.project.loteria.repositories;

import com.project.loteria.entities.Bet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BetRepository extends MongoRepository<Bet, String> {
}
