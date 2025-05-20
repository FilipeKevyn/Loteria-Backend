package com.project.loteria.dao.repositories;

import com.project.loteria.dao.NumberDAO;
import com.project.loteria.dao.mapper.NumberRowMapper;
import com.project.loteria.entities.Number;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

@Repository
public class NumberDAOImpl implements NumberDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Number findById(String id) {
        String query = "SELECT * FROM tb_number WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new NumberRowMapper(), id);
    }

    @Override
    public List<Number> findAll() {
        String query = "SELECT * FROM tb_number";
        return jdbcTemplate.query(query, new NumberRowMapper());
    }

    @Override
    public Number insert(Number number) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("tb_number");

        Map<String, Object> values = new HashMap<>();
        values.put("id", number.getId());
        values.put("number", number.getNumber());
        values.put("matched", number.getNumber());
        values.put("contest_id", number.getContest().getId());
        values.put("pool_id", number.getPool().getId());

        jdbcInsert.execute(values);
        return number;
    }
    public void insertAll(Set<Number> numbers) {
        String sql = "INSERT INTO tb_number (id, number, matched, pool_id) VALUES (?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Number number = new ArrayList<>(numbers).get(i);
                ps.setString(1, number.getId().toString());
                ps.setInt(2, number.getNumber());
                ps.setBoolean(3, number.isMatched());
                ps.setObject(4, number.getPool().getId());
            }

            @Override
            public int getBatchSize() {
                return numbers.size();
            }
        });
    }

    @Override
    public Number save(Number number) {
        String query = "UPDATE tb_number SET matched = ? WHERE id = ?";
        jdbcTemplate.update(query, number.isMatched(), number.getId());

        return number;
    }

    @Override
    public void delete(String id) {
        String query = "DELETE FROM tb_number WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

    public Number findByNumberAndPool(int numberValue, UUID poolId) {
        String query = "SELECT * FROM tb_number WHERE number = ? AND pool_id = ?";

        return jdbcTemplate.queryForObject(query, new NumberRowMapper(), numberValue, poolId);
    }

    public Number findByNumberAndContest(int numberValue, UUID contestId){
        String query = "SELECT * FROM tb_number WHERE number = ? AND contest_id = ?";

        return jdbcTemplate.queryForObject(query, new NumberRowMapper(), numberValue, contestId);
    }
}
