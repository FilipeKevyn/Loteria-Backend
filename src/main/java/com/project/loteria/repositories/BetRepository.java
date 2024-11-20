package com.project.loteria.repositories;

import com.project.loteria.entities.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BetRepository extends JpaRepository<Bet, Long> {
}
