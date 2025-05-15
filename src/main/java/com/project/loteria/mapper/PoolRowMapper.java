package com.project.loteria.mapper;

import com.project.loteria.dao.PoolDAO;
import com.project.loteria.dao.repositories.BetDAOImpl;
import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Contest;
import com.project.loteria.entities.Number;
import com.project.loteria.entities.Pool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PoolRowMapper implements RowMapper<Pool> {

    @Override
    public Pool mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null;
    }
}
