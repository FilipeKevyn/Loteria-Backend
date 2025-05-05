package com.project.loteria.repositories;

import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Pool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface BetRepository extends JpaRepository<Bet, UUID> {
    Page<Bet> findByPool(Pool pool, Pageable pageable);

    @Query("SELECT COUNT(bn) FROM Number bn JOIN bn.bets b WHERE b = :bet AND bn.matched = true")
    int countMatchedNumbersByBet(@Param("bet") Bet bet);
}
