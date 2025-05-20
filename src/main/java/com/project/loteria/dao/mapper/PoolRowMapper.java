package com.project.loteria.dao.mapper;

import com.project.loteria.entities.Pool;
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
        return pool;
    }

}
