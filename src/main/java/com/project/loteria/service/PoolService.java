package com.project.loteria.service;

import com.project.loteria.dao.repositories.PoolDAOImpl;
import com.project.loteria.dtos.PoolDTO;
import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Pool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoolService {
    @Autowired
    private PoolDAOImpl repository;

    public Pool createPool(PoolDTO poolDTO){
        Pool pool = new Pool(poolDTO);
        return repository.insert(pool);
    }

    public void deletePool(String id){
        repository.delete(id);
    }

    public Pool update(Pool pool){
        return repository.save(pool);
    }

    public Pool findById(String id){
        return repository.findById(id);
    }

    public List<Bet> getAllBets(Pool pool){
        return pool.getBets().stream().toList();
    }

    public List<Pool> findAll(){
        return repository.findAll();
    }

    public void addBetToPool(Pool pool, Bet bet){
        pool.getBets().add(bet);
        sumValueTotal(pool, bet);
        repository.save(pool);
    }

    public void sumValueTotal(Pool pool, Bet bet){
        double valueTotal = pool.getValueTotal() + bet.getValueInvested();
        pool.setValueTotal(valueTotal);
    }

    public void subtractValueTotal(Pool pool, Bet bet){
        double valueTotal = pool.getValueTotal() - bet.getValueInvested();
        pool.setValueTotal(valueTotal);
    }
}
