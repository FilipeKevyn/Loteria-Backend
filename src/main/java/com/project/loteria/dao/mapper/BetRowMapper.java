package com.project.loteria.dao.mapper;

import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Pool;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


public class BetRowMapper implements RowMapper<Bet> {

    @Override
    public Bet mapRow(ResultSet rs, int rowNum) throws SQLException {
        Bet bet = new Bet();
        bet.setId(UUID.fromString(rs.getString("bet_id")));
        bet.setValueInvested(rs.getDouble("value_invested"));
        bet.setType(rs.getString("game_type"));
        bet.setMatched(rs.getInt("matched"));

        Pool pool = new Pool();
        pool.setId(UUID.fromString(rs.getString("pool_id")));
        pool.setTitle(rs.getString("pool_title"));
        pool.setType(rs.getString("pool_type"));
        pool.setValueTotal(rs.getDouble("pool_value_total"));

        bet.setPool(pool);

        return bet;
    }
}
