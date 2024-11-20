package com.project.loteria.repositories;

import com.project.loteria.entities.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
}
