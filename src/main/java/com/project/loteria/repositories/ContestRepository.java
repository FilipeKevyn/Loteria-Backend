package com.project.loteria.repositories;

import com.project.loteria.entities.Contest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MSContestRepository extends JpaRepository<Contest, Long> {
}
