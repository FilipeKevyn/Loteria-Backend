package com.project.loteria.mapper;

import com.project.loteria.dao.repositories.PoolDAOImpl;
import com.project.loteria.entities.Contest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class ContestRowMapper implements RowMapper<Contest> {
    @Autowired
    private PoolDAOImpl poolRepository;

    @Override
    public Contest mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contest contest = new Contest();
        contest.setId(UUID.fromString(rs.getString("id")));
        //contest.setDrawnNumbers();
        contest.setPool(poolRepository.findById(rs.getString("pool_id")));

        return contest;
    }
}
