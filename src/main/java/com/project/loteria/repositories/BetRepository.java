package com.project.loteria.repositories;

import com.project.loteria.entities.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MSBetRepository extends JpaRepository<Bet, Long> {
}
