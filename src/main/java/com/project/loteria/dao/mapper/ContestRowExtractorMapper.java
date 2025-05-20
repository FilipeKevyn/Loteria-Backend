package com.project.loteria.dao.mapper;

import com.project.loteria.entities.Contest;
import com.project.loteria.entities.Number;
import com.project.loteria.entities.Pool;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ContestRowExtractorMapper implements ResultSetExtractor<Contest> {

    @Override
    public Contest extractData(ResultSet rs) throws SQLException, DataAccessException {
        Contest contest = null;
        HashSet<Number> numbers = new HashSet<>();

        while (rs.next()) {
            if (contest == null) {
                contest = new Contest();
                contest.setId(UUID.fromString(rs.getString("contest_id")));

                String poolId = rs.getString("contest_pool_id");
                if (poolId != null) {
                    Pool pool = new Pool();
                    pool.setId(UUID.fromString(poolId));
                    contest.setPool(pool);
                }
            }

            String numberId = rs.getString("number_id");
            if (numberId != null) {
                Number number = new Number();
                number.setId(UUID.fromString(numberId));
                number.setNumber(rs.getInt("number"));
                number.setMatched(rs.getBoolean("matched"));

                numbers.add(number);
            }
        }

        if (contest != null) {
            contest.setNumbers(numbers);
        }

        return contest;
    }
}
