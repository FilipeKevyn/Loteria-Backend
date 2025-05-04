package com.project.loteria.dao;

import com.project.loteria.entities.Contest;

public interface ContestDAO {
    Contest findById(String id);
    Contest insert(Contest contest);
    Contest save(Contest contest);
    void delete(String id);
}
