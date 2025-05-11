package com.project.loteria.service;

import org.springframework.stereotype.Service;

@Service
public class ResultService {
//    @Autowired
//    private PoolService poolService;
//
//    private final BetService betService;
//
//    @Autowired
//    private BetNumberService betNumberService;
//
//    @Autowired
//    public ResultService(BetService betService) {
//        this.betService = betService;
//    }
//
//    public void verifyAllBets(UUID poolId){
//        Pool pool = poolService.findById(poolId);
//        verifyBetNumbers(pool);
//
//        List<Bet> bets = poolService.getAllBets(pool);
//        for (Bet bet : bets){
//            verifyBet(bet);
//        }
//    }
//
//    public void verifyBetNumbers(Pool pool){
//        Contest contest = pool.getContest();
//        for (BetNumber number : pool.getBetNumbers()){
//            if (contest.getDrawnNumbers().contains(number.getNumber())){
//                number.setMatched(true);
//                betNumberService.insert(number);
//            };
//        }
//    }
//
//    public void verifyBet(Bet bet){
//        betService.setMatched(bet, betService.countMatched(bet));
//    }
//
//    public int verifyMatched(List<Integer> bet, List<Integer> drawNumbers){
//        int count = 0;
//
//        for (Integer number : drawNumbers){
//            if (bet.contains(number)){
//                count++;
//            }
//        }
//
//        return count;
//    }
}
