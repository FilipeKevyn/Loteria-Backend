package com.project.loteria.interfaces;

import java.util.List;

public interface ResultService<T, B, C> {
    T insertBet(B bet);
    List<T> insertContest(C contest);
}
