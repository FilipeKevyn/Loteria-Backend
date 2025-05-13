package com.project.loteria.repositories;

import com.project.loteria.entities.Number;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface NumberRepository extends MongoRepository<Number, String> {
}
