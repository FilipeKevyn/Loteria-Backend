package com.project.loteria.dao.repositories;

import com.project.loteria.dao.BetDAO;
import com.project.loteria.dao.mapper.BetRowExtractorMapper;
import com.project.loteria.dao.mapper.BetRowMapper;
import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Number;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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

        Bet bet = jdbcTemplate.queryForObject(query, new BetRowMapper(), id);

        String queryNumbers = """
        SELECT n.id, n.number, n.matched
        FROM tb_number n
        JOIN tb_numbers_bet nb ON n.id = nb.number_id
        WHERE nb.bet_id = ?
        """;

        List<Number> numbers = jdbcTemplate.query(queryNumbers, (rs, rowNum) -> {
            Number number = new Number();
            number.setId(UUID.fromString(rs.getString("id")));
            number.setNumber(rs.getInt("number"));
            number.setMatched(rs.getBoolean("matched"));
            return number;
        }, id);

        return bet;
    }

    @Override
    public Set<Bet> findBetsByPool(String poolId) {
        String query = """
        SELECT 
            b.*,
            n.id AS number_id,
            n.number,
            n.matched AS number_matched
        FROM tb_bet b
        LEFT JOIN tb_numbers_bet nb ON nb.bet_id = b.id
        LEFT JOIN tb_number n ON n.id = nb.number_id
        WHERE b.pool_id = ?
        ORDER BY b.id
    """;

        List<Bet> bets = jdbcTemplate.query(query, new BetRowExtractorMapper(), poolId);
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

    @Override
    public List<Bet> findAll() {
        String query = "SELECT * FROM tb_bet";
        return jdbcTemplate.query(query, new BetRowMapper());
    }
}
