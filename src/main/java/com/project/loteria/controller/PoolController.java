package com.project.loteria.controller;

import com.project.loteria.dtos.PoolDTO;
import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Pool;
import com.project.loteria.service.PoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pool")
public class PoolController {

    @Autowired
    private PoolService service;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/create")
    public ResponseEntity<Pool> create(@RequestBody PoolDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createPool(dto));
    }

    @GetMapping(value = "/{poolId}/bets")
    public List<Bet> getAllBets(@PathVariable Long poolId){
        return service.getAllBets(service.findById(poolId));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<Pool>> getAll(){
        return ResponseEntity.ok().body(service.findAll());
    }
}
