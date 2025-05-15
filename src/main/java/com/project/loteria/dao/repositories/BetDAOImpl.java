package com.project.loteria.dao.repositories;

import com.project.loteria.dao.BetDAO;
import com.project.loteria.dao.PoolDAO;
import com.project.loteria.entities.Bet;
import com.project.loteria.mapper.BetRowMapper;
import com.project.loteria.mapper.BetWithPoolRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BetDAOImpl implements BetDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Bet findById(String id) {
        String query = """
        SELECT b.*, 
               p.id as pool_id, 
               p.title as pool_title, 
               p.type as pool_type, 
               p.value_total_invested as pool_value_total
        FROM tb_bet b
        JOIN tb_pool p ON b.pool_id = p.id
        WHERE b.id = ?
        """;

        return jdbcTemplate.queryForObject(query, new BetWithPoolRowMapper(), id);
    }

    @Override
    public Set<Bet> findBetsByPool(String poolId) {
        String query = "SELECT * FROM tb_bet WHERE pool_id = ?";
        List<Bet> bets = jdbcTemplate.query(query, new BetRowMapper(), poolId);

        return new HashSet<>(bets);
    }

    @Override
    public Bet insert(Bet bet) {
        System.out.println("\n ***  INSERINDO BET*** \n");

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("tb_bet");

        Map<String, Object> values = new HashMap<>();
        values.put("id", bet.getId());
        values.put("value_invested", bet.getValueInvested());
        values.put("game_type", bet.getGameType());
        values.put("matched", bet.getMatched());
        values.put("pool_id", bet.getPool().getId());

        simpleJdbcInsert.execute(values);

        System.out.println("\n ***  BET INSERIDA  *** \n");

        return bet;
    }

    @Override
    public Bet save(Bet bet) {
        System.out.println("\n ***  ATUALIZANDO BET  *** \n");

        String query = "UPDATE tb_bet SET value_invested = ?, matched = ? WHERE id = ?";
        jdbcTemplate.update(query, bet.getValueInvested(), bet.getMatched(), bet.getId());

        System.out.println("\n ***  BET ATUALIZADO  *** \n");
        return bet;
    }

    @Override
    public Set<Bet> saveAll(Set<Bet> bets) {
        for (Bet bet : bets) {
            save(bet);
        }
        return bets;
    }

    @Override
    public void delete(String id) {
        String query = "DELETE FROM tb_bet WHERE id = ?";
        jdbcTemplate.update(query, id);
    }
}
