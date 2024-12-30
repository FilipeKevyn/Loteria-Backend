package com.project.loteria.megasena.controller;

import com.project.loteria.megasena.entities.MSBet;
import com.project.loteria.megasena.entities.MSPool;
import com.project.loteria.megasena.service.MSPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.processing.Generated;
import java.util.List;

@RestController
@RequestMapping(value = "/pool_megasena")
public class MSPoolController {
    @Autowired
    private MSPoolService service;

    @PostMapping(value = "/create")
    public MSPool creatPool(String name){
        return service.createPool(name);
    }

    @GetMapping(value = "/{poolId}/bets")
    public List<MSBet> getAllBets(@PathVariable Long poolId){
        return service.getAllBets(service.findById(poolId));
    }

    @GetMapping
    public ResponseEntity<List<MSPool>> getAll(){
        return ResponseEntity.ok().body(service.findAll());
    }
}
