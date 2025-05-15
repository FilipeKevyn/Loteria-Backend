package com.project.loteria.dao.repositories;

import com.project.loteria.dao.NumberDAO;
import com.project.loteria.entities.Number;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NumberDAOImpl implements NumberDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
}
