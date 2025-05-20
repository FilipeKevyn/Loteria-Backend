package com.project.loteria.dao.mapper;

import com.project.loteria.entities.Contest;
import com.project.loteria.entities.Number;
import com.project.loteria.entities.Pool;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class NumberRowMapper implements RowMapper<Number> {
    @Override
    public Number mapRow(ResultSet rs, int rowNum) throws SQLException {
        Number number = new Number();
        number.setId(UUID.fromString(rs.getString("id")));
        number.setMatched(rs.getBoolean("matched"));
        number.setNumber(rs.getInt("number"));

        Pool pool = new Pool();
        pool.setId(UUID.fromString(rs.getString("pool_id")));

        Contest contest = new Contest();
        UUID contestId = Optional.ofNullable(rs.getString("contest_id"))
                .filter(s -> !s.isBlank())
                .map(UUID::fromString)
                .orElse(null);

        contest.setId(contestId);

        number.setPool(pool);
        number.setContest(contest);

        return number;
    }
}
