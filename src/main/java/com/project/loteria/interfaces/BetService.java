package com.project.loteria.interfaces;

import com.project.loteria.exceptions.ValidateException;

import java.util.List;

public interface BetService<T> {
    T insert(T obj);
    void validate(Integer[] numbers);
}
