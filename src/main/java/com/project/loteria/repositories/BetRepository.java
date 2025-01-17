package com.project.loteria.repositories;

import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Pool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetRepository extends JpaRepository<Bet, Long> {
    Page<Bet> findByPool(Pool pool, Pageable pageable);
}
