package com.project.loteria.dao.mapper;

import com.project.loteria.entities.Contest;
import com.project.loteria.entities.Pool;
import com.project.loteria.entities.Number;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PoolRowExtractorMapper implements ResultSetExtractor<Pool> {

    @Override
    public Pool extractData(ResultSet rs) throws SQLException, DataAccessException {
        Pool pool = null;
        Set<Number> numbers = new HashSet<>();

        while (rs.next()) {
            if (pool == null) {
                pool = new Pool();
                pool.setId(UUID.fromString(rs.getString("pool_id")));
                pool.setTitle(rs.getString("pool_title"));
                pool.setType(rs.getString("pool_type"));
                pool.setValueTotal(rs.getDouble("value_total_invested"));

                String contestId = rs.getString("contest_id");
                if (contestId != null) {
                    Contest contest = new Contest();
                    contest.setId(UUID.fromString(contestId));
                    pool.setContest(contest);
                }
            }

            String numberId = rs.getString("number_id");
            if (numberId != null) {
                Number number = new Number();
                number.setId(UUID.fromString(numberId));
                number.setNumber(rs.getInt("number"));
                number.setMatched(rs.getBoolean("number_matched"));
                numbers.add(number);
            }
        }

        if (pool != null) {
            pool.setNumbers(numbers);
        }

        return pool;
    }
}