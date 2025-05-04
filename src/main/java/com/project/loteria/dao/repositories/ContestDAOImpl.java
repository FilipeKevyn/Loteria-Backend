package com.project.loteria.dao.repositories;

import com.project.loteria.dao.ContestDAO;
import com.project.loteria.entities.Contest;
import com.project.loteria.mapper.ContestRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ContestDAOImpl implements ContestDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Contest findById(String id) {
        String query = "SELECT * FROM tb_contest WHERE id = ?";
        Contest contest = jdbcTemplate.queryForObject(query, new ContestRowMapper(), id);

        return contest;
    }

    @Override
    public Contest insert(Contest contest) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        Map<String, Object> values = new HashMap<>();
        return new Contest();
    }

    @Override
    public Contest save(Contest contest) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
