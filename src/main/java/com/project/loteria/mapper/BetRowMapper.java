package com.project.loteria.mapper;

import com.project.loteria.dao.repositories.PoolDAOImpl;
import com.project.loteria.entities.Bet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class BetRowMapper implements RowMapper<Bet> {
    @Autowired
    private PoolDAOImpl poolRepository;

    @Override
    public Bet mapRow(ResultSet rs, int rowNum) throws SQLException {
        Bet bet = new Bet();
        bet.setId(UUID.fromString(rs.getString("id")));
        bet.setValueInvested(rs.getDouble("value_invested"));
        // setar betNumbers
        bet.setMatched(rs.getInt("matched"));
        bet.setType(rs.getString("game_type"));
        // se lembrar de criar a FK pool_id para funcionar
        bet.setPool(poolRepository.findById(rs.getString("pool_id")));

        return bet;
    }
}
