package com.project.loteria.megasena.service;

import com.project.loteria.interfaces.PoolService;
import com.project.loteria.megasena.entities.MSBet;
import com.project.loteria.megasena.entities.MSPool;
import com.project.loteria.megasena.repositories.MSPoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MSPoolService implements PoolService<MSPool, MSBet> {
    @Autowired
    private MSPoolRepository repository;

    public MSPool createPool(){
        MSPool pool = new MSPool();
        return repository.save(pool);
    }

    public MSPool findById(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Pool not found with id "+ id));
    }

    public List<MSBet> getAllBets(MSPool pool){
        return pool.getBets().stream().toList();
    }

    public List<MSPool> findAll(){
        return repository.findAll();
    }

    public void addBetToPool(MSPool pool, MSBet bet){
        pool.getBets().add(bet);
        repository.save(pool);
    }

    public void getValueTotal(Long id){
        MSPool pool = findById(id);
        double valueTotal = pool.getBets().stream().mapToDouble(MSBet::getValueInvested).sum();
        pool.setValueTotal(valueTotal);
    }
}
