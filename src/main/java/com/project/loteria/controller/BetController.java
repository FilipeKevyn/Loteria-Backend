package com.project.loteria.controller;

import com.project.loteria.entities.Bet;
import com.project.loteria.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/bet")
public class BetController {
    @Autowired
    private BetService betService;

    @GetMapping(value = "/{id}")
    private ResponseEntity<Bet> findById(@PathVariable Long id){
        Bet bet = betService.findById(id);
        return ResponseEntity.ok().body(bet);
    }

    @PostMapping
    public ResponseEntity<Bet> insert(@RequestBody Bet obj){
        System.out.println("       | INSERINDO BET | no controller        ");
        obj = betService.insert(obj);
        return ResponseEntity.ok().body(obj); // trocar para um Response created
    }
}
