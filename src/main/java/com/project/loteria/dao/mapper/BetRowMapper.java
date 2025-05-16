package com.project.loteria.dao.mapper;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.project.loteria.dao.repositories.PoolDAOImpl;
import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Pool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


public class BetRowMapper implements RowMapper<Bet> {

    @Override
    public Bet mapRow(ResultSet rs, int rowNum) throws SQLException {
        Bet bet = new Bet();

        bet.setId(UUID.fromString(rs.getString("id")));
        bet.setValueInvested(rs.getDouble("value_invested"));
        bet.setMatched(rs.getInt("matched"));
        bet.setType(rs.getString("game_type"));

        Pool pool = new Pool();
        pool.setId(UUID.fromString(rs.getString("pool_id")));
        pool.setTitle(rs.getString("pool_title"));
        pool.setType(rs.getString("pool_type"));
        pool.setValueTotal(rs.getDouble("pool_value_total"));

        bet.setPool(pool);

        return bet;
    }
}
