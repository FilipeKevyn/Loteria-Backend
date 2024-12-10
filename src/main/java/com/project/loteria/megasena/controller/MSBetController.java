package com.project.loteria.megasena.controller;

import com.project.loteria.megasena.dtos.MSBetDTO;
import com.project.loteria.megasena.entities.MSBet;
import com.project.loteria.megasena.service.MSBetService;
import com.project.loteria.megasena.service.MSResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/megasena_bet")
public class MSBetController {
    @Autowired
    private MSResultService resultService;

    @Autowired
    private MSBetService betService;

    @GetMapping(value = "/{id}")
    private ResponseEntity<MSBet> findById(@PathVariable Long id){
        MSBet bet = betService.findById(id);
        return ResponseEntity.ok().body(bet);
    }

    @PostMapping(value = "/{poolId}")
    public ResponseEntity<MSBet> addBet(@PathVariable Long poolId, @RequestBody MSBetDTO obj){
        MSBet bet = new MSBet(obj);
        betService.addBetToPool(poolId, bet);
        return ResponseEntity.status(HttpStatus.CREATED).body(bet);
    }
}
