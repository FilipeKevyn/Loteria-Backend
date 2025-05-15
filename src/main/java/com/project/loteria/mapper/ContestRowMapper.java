package com.project.loteria.mapper;

import com.project.loteria.dao.repositories.PoolDAOImpl;
import com.project.loteria.entities.Contest;
import com.project.loteria.entities.Number;
import com.project.loteria.entities.Pool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ContestRowMapper implements RowMapper<Contest> {

    @Override
    public Contest mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pool pool = new Pool();
        pool.setId(UUID.fromString(rs.getString("pool_id")));
        pool.setTitle(rs.getString("pool_title"));
        pool.setType(rs.getString("pool_type"));
        pool.setValueTotal(rs.getDouble("value_total_invested"));

        Contest contest = new Contest();
        contest.setId(UUID.fromString(rs.getString("id")));

        Set<Number> numbers = new HashSet<>();

        while (rs.next() && rs.getString("contest_id").equals(contest.getId().toString())){
            Number number = new Number();
            number.setId(UUID.fromString(rs.getString("number_id")));
            number.setNumber(rs.getInt("number"));
            number.setMatched(rs.getBoolean("number_matched"));
            numbers.add(number);
        }

        return contest;
    }
}
