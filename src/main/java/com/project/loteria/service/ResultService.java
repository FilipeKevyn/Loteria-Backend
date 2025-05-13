package com.project.loteria.service;

import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Number;
import com.project.loteria.entities.Contest;
import com.project.loteria.entities.Pool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ResultService {
    @Autowired
    private PoolService poolService;

    private final BetService betService;

    @Autowired
    private NumberService numberService;

    @Autowired
    public ResultService(BetService betService) {
        this.betService = betService;
    }

    public void verifyAllBets(Pool pool){
        List<Bet> bets = poolService.getAllBets(pool);
        for (Bet bet : bets){
            setMatched(bet);
        }
    }

    public void verifyBetNumbers(Pool pool){
        Contest contest = pool.getContest();
        for (Number number : pool.getNumbers()){
            if (contest.getDrawnNumbers().contains(number.getNumber())){
                number.setMatched(true);
                numberService.insert(number);
            };
        }
        verifyAllBets(pool);
    }

    public void updateContest(Pool pool, Contest contest){
        for (Number number : pool.getNumbers()){
            if (!contest.getDrawnNumbers().contains(number.getNumber())){
                number.setMatched(false);
                numberService.insert(number);
            }
        }
        verifyAllBets(pool);
    }

    public void setMatched(Bet bet){
//        betService.setMatched(bet, betService.countMatched(bet));
    }
}
