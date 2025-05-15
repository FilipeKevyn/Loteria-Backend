package com.project.loteria.dao.repositories;

import com.project.loteria.dao.ContestDAO;
import com.project.loteria.entities.Contest;
import com.project.loteria.mapper.ContestRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class ContestDAOImpl implements ContestDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PoolDAOImpl poolRepository;

    @Override
    public Contest findById(String id) {
        String query = "SELECT c.id AS contest_id, c.pool_id, p.id AS pool_id, p.title AS pool_title, p.type AS pool_type, p.value_total_invested, " +
                "n.id AS number_id, n.number, n.matched AS number_matched " +
                "FROM tb_contest c " +
                "JOIN tb_pool p ON c.pool_id = p.id " +
                "LEFT JOIN tb_number n ON n.contest_id = c.id " +
                "WHERE c.id = ?";

        Contest contest = jdbcTemplate.queryForObject(query, new ContestRowMapper(), id);

        return contest;
    }

    @Override
    public Contest insert(Contest contest) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("tb_contest");

        Map<String, Object> values = new HashMap<>();
        values.put("id",contest.getId());
        values.put("pool_id", contest.getPool().getId());

        String insertNumber = "INSERT INTO tb_number (id, number, matched, contest_id, pool_id) VALUES (?, ?, ?, ?, ?)";
        for (Integer num : contest.getDrawnNumbers()){
            UUID numberId = UUID.randomUUID();
            jdbcTemplate.update(insertNumber, numberId, num, false, contest.getId(), contest.getPool().getId());
        }

        jdbcInsert.execute(values);

        return new Contest();
    }

    @Override
    public Contest save(Contest contest) {
        String selectQuery = "SELECT id FROM tb_pool WHERE id = ?";
        UUID existingContestId;
        try {
            existingContestId = jdbcTemplate.queryForObject(selectQuery, UUID.class, contest.getPool().getId());
        }
        catch (EmptyResultDataAccessException e){
        }

        String insertContest = "INSERT INTO tb_contest (id, poo_id) VALUES (?,?)";
        UUID newContestId = UUID.randomUUID();
        jdbcTemplate.update(insertContest, newContestId, contest.getPool().getId());

        String insertNumber = "INSERT INTO tb_number (id, number, matched, contest_id, pool_id) VALUES (?, ?, ?, ?, ?)";
        for (Integer num : contest.getDrawnNumbers()){
            UUID numberId = UUID.randomUUID();
            jdbcTemplate.update(insertNumber, numberId, num, false, contest.getId(), contest.getPool().getId());
        }

        contest.setId(newContestId);
        return contest;
    }

    @Override
    public void delete(String id) {

    }
}
