package com.project.loteria.megasena.controller;

import com.project.loteria.dtos.BetDTO;
import com.project.loteria.entities.Bet;
import com.project.loteria.megasena.service.MSBetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/megasena_bet")
public class MSBetController {
    @Autowired
    private MSBetService betService;

    @GetMapping(value = "/{id}")
    private ResponseEntity<Bet> findById(@PathVariable Long id){
        Bet bet = betService.findById(id);
        return ResponseEntity.ok().body(bet);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/{poolId}")
    public ResponseEntity<Bet> addBet(@PathVariable Long poolId, @RequestBody BetDTO obj){
        Bet bet = new Bet(obj);
        betService.addBetToPool(poolId, bet);
        return ResponseEntity.status(HttpStatus.CREATED).body(bet);
    }
}
