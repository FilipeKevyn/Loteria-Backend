package com.project.loteria.megasena.service;

import com.project.loteria.interfaces.ResultService;
import com.project.loteria.megasena.entities.MSPool;
import com.project.loteria.megasena.entities.MSResult;
import com.project.loteria.megasena.repositories.MSResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MSResultService implements ResultService<MSResult> {
    @Autowired
    private MSResultRepository resultRepository;

    private MSPoolService poolService;

    public MSResult creat(){
        return resultRepository.save(new MSResult());
    }

    public MSResult addResultToPool(Long poolId, MSResult result){
        result = creat();
        MSPool pool = poolService.findById(poolId);
        poolService.addResultToPool(pool, result);
        return result;
    }
}
