package com.project.loteria.dao;

import com.project.loteria.entities.Pool;

public interface PoolDAO {
    Pool findById(String id);
    Pool insert(Pool pool);
    Pool save(Pool pool);
    void delete(String id);
}
