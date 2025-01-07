package com.project.loteria.interfaces;

import com.project.loteria.entities.Contest;

public interface ContestService {
    Contest insert(Contest obj);
    void setContestInPool(Long Id, Contest contest);
}
