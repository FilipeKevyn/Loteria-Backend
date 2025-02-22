package com.project.loteria.repositories;

import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Pool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BetRepository extends JpaRepository<Bet, UUID> {
    Page<Bet> findByPool(Pool pool, Pageable pageable);
}
