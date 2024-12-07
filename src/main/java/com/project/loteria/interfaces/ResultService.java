package com.project.loteria.interfaces;

import com.project.loteria.megasena.entities.MSResult;

import java.util.List;

public interface ResultService<T> {
    T addResultToPool(Long id, T result);
}
