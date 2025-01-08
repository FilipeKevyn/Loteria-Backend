package com.project.loteria.interfaces;

import java.util.List;

public interface ResultService {
    void verifyAllBets(Long poolId);
    int verifyMatched(List<Integer> bet, List<Integer> drawNumbers);

}
