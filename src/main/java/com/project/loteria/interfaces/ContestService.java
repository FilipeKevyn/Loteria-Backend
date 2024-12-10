package com.project.loteria.interfaces;

public interface ContestService<T> {
    T insert(T obj);
    void setContestInPool(Long Id, T contest);
}
