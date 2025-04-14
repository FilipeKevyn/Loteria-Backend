package com.project.loteria.repositories;

import com.project.loteria.entities.BetNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BetNumberRepository extends JpaRepository<BetNumber, UUID> {
}
