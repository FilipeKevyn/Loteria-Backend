package com.project.loteria.megasena.controller;

import com.project.loteria.megasena.dtos.MSPoolDTO;
import com.project.loteria.entities.MSBet;
import com.project.loteria.entities.MSPool;
import com.project.loteria.megasena.service.MSPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pool_megasena")
public class MSPoolController {
    @Autowired
    private MSPoolService service;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/create")
    public ResponseEntity<MSPool> creatPool(@RequestBody MSPoolDTO poolDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createPool(poolDTO));
    }

    @GetMapping(value = "/{poolId}/bets")
    public List<MSBet> getAllBets(@PathVariable Long poolId){
        return service.getAllBets(service.findById(poolId));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<MSPool>> getAll(){
        return ResponseEntity.ok().body(service.findAll());
    }
}
