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

    public MSPool createPool(String name){
        MSPool pool = new MSPool(name);
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
        setValueTotal(pool, bet);
        repository.save(pool);
    }

    public void setValueTotal(MSPool pool, MSBet bet){
        double valueTotal = pool.getValueTotal() + bet.getValueInvested();
        pool.setValueTotal(valueTotal);
    }
}
