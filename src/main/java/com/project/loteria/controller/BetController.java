package com.project.loteria.controller;

import com.project.loteria.dtos.BetDTO;
import com.project.loteria.entities.Bet;
import com.project.loteria.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

    @GetMapping(value = "/{poolId}/")
    public ResponseEntity<Page<Bet>> getBetsByPool(@PathVariable Long poolId,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Bet> bets = betService.findBetsByPool(poolId, pageable);
        return ResponseEntity.ok().body(bets);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/{poolId}")
    public ResponseEntity<Bet> addBet(@PathVariable Long poolId, @RequestBody BetDTO obj){
        Bet bet = new Bet(obj);
        betService.addBetToPool(poolId, bet);
        return ResponseEntity.status(HttpStatus.CREATED).body(bet);
    }
}
