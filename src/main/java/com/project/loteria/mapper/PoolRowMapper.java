package com.project.loteria.mapper;

import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Contest;
import com.project.loteria.entities.Pool;
import com.project.loteria.entities.Number;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PoolRowMapper implements RowMapper<Pool> {

    @Override
    public Pool mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pool pool = new Pool();

        pool.setId(UUID.fromString(rs.getString("pool_id")));
        pool.setTitle(rs.getString("pool_title"));
        pool.setType(rs.getString("pool_type"));
        pool.setValueTotal(rs.getDouble("value_total_invested"));

        Contest contest = null;
        Bet bet = null;
        Set<Number> numbers = new HashSet<>();

        if (rs.getString("contest_id") != null) {
            contest = new Contest();
            contest.setId(UUID.fromString(rs.getString("contest_id")));
            pool.setContest(contest);  // Adiciona o Contest ao Pool
        }

        if (rs.getString("bet_id") != null) {
            bet = new Bet();
            bet.setId(UUID.fromString(rs.getString("bet_id")));
            bet.setValueInvested(rs.getDouble("value_invested"));
            bet.setMatched(rs.getInt("bet_matched"));
            bet.setType((rs.getString("game_type")));
            pool.getBets().add(bet);
        }

        if (rs.getString("number_id") != null) {
            Number number = new Number(UUID.fromString(rs.getString("number_id")),
                    pool,
                    bet,
                    rs.getInt("number"),
                    rs.getBoolean("number_matched"),
                    contest);

            pool.getNumbers().add(number);

            if (bet != null) {
                bet.getBetNumbers().add(number);
            }
        }

        return pool;
    }
}