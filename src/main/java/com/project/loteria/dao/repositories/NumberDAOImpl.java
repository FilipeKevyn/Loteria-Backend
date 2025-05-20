package com.project.loteria.dao.repositories;

import com.project.loteria.dao.NumberDAO;
import com.project.loteria.dao.mapper.NumberRowMapper;
import com.project.loteria.entities.Number;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
