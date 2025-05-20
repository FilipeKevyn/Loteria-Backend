package com.project.loteria.dao.mapper;

import com.project.loteria.entities.Contest;
import com.project.loteria.entities.Pool;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ContestRowMapper implements RowMapper<Contest> {
    @Override
    public Contest mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contest contest = new Contest();
        contest.setId(UUID.fromString(rs.getString("id")));

        Pool pool = new Pool();
        pool.setId(UUID.fromString(rs.getString("pool_id")));

        contest.setPool(pool);

        return contest;
    }
}
