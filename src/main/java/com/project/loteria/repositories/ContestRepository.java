package com.project.loteria.repositories;

import com.project.loteria.entities.Contest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestRepository extends MongoRepository<Contest, String> {
}
