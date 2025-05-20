package com.project.loteria.dao.repositories;

import com.project.loteria.dao.ContestDAO;
import com.project.loteria.dao.mapper.ContestRowMapper;
import com.project.loteria.entities.Contest;
import com.project.loteria.dao.mapper.ContestRowExtractorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class ContestDAOImpl implements ContestDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Contest findById(String id) {
        String query = """
            SELECT 
                c.id AS contest_id,
                c.pool_id AS contest_pool_id,
                n.id AS number_id,
                n.number,
                n.matched
            FROM tb_contest c
            LEFT JOIN tb_number n ON n.contest_id = c.id
            WHERE c.id = ?
        """;

        Contest contest = jdbcTemplate.query(query, new ContestRowExtractorMapper(), id);

        return contest;
    }

    @Override
    public List<Contest> findAll() {
        String query = "SELECT * FROM tb_contest";
        return jdbcTemplate.query(query, new ContestRowMapper());
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
        String query = "DELETE FROM tb_contest WHERE id = ?";
        jdbcTemplate.update(query, id);
    }
}
