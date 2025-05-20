package com.project.loteria.dao.mapper;

import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Number;
import com.project.loteria.entities.Pool;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BetRowExtractorMapper implements ResultSetExtractor<List<Bet>> {
    @Override
    public List<Bet> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<String, Bet> betMap = new HashMap<>();

        while (rs.next()) {
            UUID betId = UUID.fromString(rs.getString("bet_id"));
            Bet bet = betMap.get(betId);

            if (bet == null) {
                bet = new Bet();
                bet.setId(betId);
                bet.setValueInvested(rs.getDouble("value_invested"));
                bet.setType(rs.getString("game_type"));
                bet.setMatched(rs.getInt("matched"));

                Pool pool = new Pool();
                pool.setId(UUID.fromString(rs.getString("pool_id")));
                bet.setPool(pool);

                bet.setBetNumbers(new HashSet<>());
                betMap.put(betId.toString(), bet);
            }

            UUID numberId = UUID.fromString(rs.getString("number_id"));
            if (numberId != null) {
                Number number = new Number();
                number.setId(numberId);
                number.setNumber(rs.getInt("number"));
                number.setMatched(rs.getBoolean("number_matched"));

                bet.getBetNumbers().add(number);
            }
        }

        return new ArrayList<>(betMap.values());
    }
}
