package com.project.loteria.mapper;

import com.project.loteria.dao.PoolDAO;
import com.project.loteria.dao.repositories.BetDAOImpl;
import com.project.loteria.entities.Pool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PoolRowMapper implements RowMapper<Pool> {

    @Override
    public Pool mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pool pool = new Pool();
        pool.setId(UUID.fromString(rs.getString("id")));
        pool.setTitle(rs.getString("title"));
        pool.setType(rs.getString("type"));
        pool.setValueTotal(rs.getDouble("value_total_invested"));
        // setar o contest
        // setar users
        // setar betnumbers

        return pool;
    }
}
