package com.project.loteria.megasena.controller;

import com.project.loteria.megasena.dtos.MSBetDTO;
import com.project.loteria.megasena.entities.MSBet;
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
    private ResponseEntity<MSBet> findById(@PathVariable Long id){
        MSBet bet = betService.findById(id);
        return ResponseEntity.ok().body(bet);
    }

    @PostMapping
    public ResponseEntity<MSBet> insert(@RequestBody MSBetDTO obj){
        System.out.println("       | INSERINDO BET | no controller        ");
        MSBet bet = new MSBet(obj);
        bet = betService.insert(bet);
        return ResponseEntity.status(HttpStatus.CREATED).body(bet); // trocar para um Response created
    }
}
