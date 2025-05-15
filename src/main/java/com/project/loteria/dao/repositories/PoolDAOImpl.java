package com.project.loteria.dao.repositories;

import com.project.loteria.dao.PoolDAO;
import com.project.loteria.entities.Pool;
import com.project.loteria.mapper.BetRowMapper;
import com.project.loteria.mapper.PoolRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PoolDAOImpl implements PoolDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Pool findById(String id) {
        String query = "SELECT " +
                "p.id AS pool_id, p.title AS pool_title, p.type AS pool_type, p.value_total_invested, " +
                "c.id AS contest_id, " +
                "n.id AS number_id, n.number, n.matched AS number_matched, " +
                "b.id AS bet_id, b.value_invested, b.game_type, b.matched AS bet_matched " +
                "FROM tb_pool p " +
                "LEFT JOIN tb_contest c ON c.pool_id = p.id " +
                "LEFT JOIN tb_number n ON n.pool_id = p.id " +
                "LEFT JOIN tb_numbers_bet nb ON nb.number_id = n.id " +
                "LEFT JOIN tb_bet b ON b.id = nb.bet_id " +
                "WHERE p.id = ?";

        Pool pool = jdbcTemplate.queryForObject(query, new PoolRowMapper(), id);

        return pool;
    }

    @Override
    public Pool insert(Pool pool) {
        System.out.println("\n ***  INSERINDO POOL*** \n");

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("tb_pool");

        Map<String, Object> values = new HashMap<>();
        values.put("id", pool.getId());
        values.put("title", pool.getTitle());
        values.put("type", pool.getType());

        jdbcInsert.execute(values);

        return pool;
    }

    @Override
    public Pool save(Pool pool) {
        System.out.println("\n ***  ATUALIZANDO POOL*** \n");

        String query = """
                UPDATE tb_pool SET
                    value_total_invested = ?
                WHERE id = ?
                """;

        jdbcTemplate.update(query, pool.getValueTotal(), pool.getId());

        System.out.println("\n ***  POOL ATUALIZADO  *** \n");

        return pool;
    }

    @Override
    public void delete(String id) {
        String query = "DELETE FROM tb_bet WHERE id = ?";
        jdbcTemplate.update(query, id);
    }
}
