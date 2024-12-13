package com.project.loteria.megasena.service;

import com.project.loteria.megasena.entities.MSBet;
import com.project.loteria.megasena.entities.MSContest;
import com.project.loteria.megasena.entities.MSPool;
import com.project.loteria.megasena.entities.MSResult;
import com.project.loteria.megasena.repositories.MSResultRepository;
import com.project.loteria.megasena.service.MSResultService;
import com.project.loteria.megasena.service.MSPoolService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MSResultServiceTest {

    @Mock
    private MSResultRepository resultRepository;

    @Mock
    private MSPoolService poolService;

    @Mock
    private MSBetService betService;

    @InjectMocks
    private MSResultService resultService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(resultRepository.save(any(MSResult.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void testVerifyAllBets() {
        // Configuração
        Long poolId = 1L;
        Integer[] drawNumbers = {1, 10, 16, 24, 33, 50};
        MSPool pool = mock(MSPool.class);

        MSContest contest = new MSContest(1L, drawNumbers, new Date());
        when(pool.getContest()).thenReturn(contest);

        MSBet bet1 = new MSBet(1L, "123" , new Integer[]{1, 10, 15, 25, 30, 50}, 6);
        MSBet bet2 = new MSBet(2L, "234", new Integer[]{2, 11, 16, 24, 35, 40}, 6);
        MSBet bet3 = new MSBet(3L, "652", new Integer[]{1, 10, 16, 24, 33, 50}, 6);

        List<MSBet> bets = new ArrayList<>();
        bets.add(bet1);
        bets.add(bet2);
        bets.add(bet3);

        when(poolService.findById(poolId)).thenReturn(pool);
        when(poolService.getAllBets(pool)).thenReturn(bets);

        resultService.verifyAllBets(poolId);

        verify(poolService, times(3)).addResultToPool(eq(pool), any(MSResult.class));

        assertEquals(3, resultService.verifyMatched(bet1.getBet(), drawNumbers)); // 3 matches
        assertEquals(2, resultService.verifyMatched(bet2.getBet(), drawNumbers)); // 2 matches
        assertEquals(6, resultService.verifyMatched(bet3.getBet(), drawNumbers)); // 6 matches
    }
}