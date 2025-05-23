package com.project.loteria.repositories;

import com.project.loteria.entities.Contest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContestRepository extends JpaRepository<Contest, UUID> {
}
