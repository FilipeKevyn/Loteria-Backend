package com.project.loteria.megasena.controller;

import com.project.loteria.megasena.entities.MSBet;
import com.project.loteria.megasena.entities.MSContest;
import com.project.loteria.megasena.entities.MSPool;
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

    @PostMapping("/creat")
    public ResponseEntity<Void> creatPool(){
        service.creatPool();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping(value = "/{poolId}")
    public ResponseEntity<List<MSBet>> getAllBets(@PathVariable Long poolId){
        return ResponseEntity.ok().body(service.getAllBets(service.findById(poolId)));
    }

    @GetMapping
    public ResponseEntity<List<MSPool>> getAll(){
        return ResponseEntity.ok().body(service.findAll());
    }
}
