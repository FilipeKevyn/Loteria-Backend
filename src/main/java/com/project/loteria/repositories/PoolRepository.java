package com.project.loteria.repositories;

import com.project.loteria.entities.Pool;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoolRepository extends MongoRepository<Pool, String> {
}
