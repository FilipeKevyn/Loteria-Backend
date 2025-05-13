package com.project.loteria.repositories;

import com.project.loteria.entities.Pool;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PoolRepository extends MongoRepository<Pool, String> {
}
