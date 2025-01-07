package com.project.loteria.repositories;

import com.project.loteria.entities.Pool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MSPoolRepository extends JpaRepository<Pool, Long> {
}
