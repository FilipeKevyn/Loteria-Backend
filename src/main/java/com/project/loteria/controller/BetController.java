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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/{poolId}/")
    public ResponseEntity<Page<Bet>> findByPool(@PathVariable Long poolId,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Bet> bets = betService.findBetsByPool(poolId, pageable);
        return ResponseEntity.ok().body(bets);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/{poolId}")
    public ResponseEntity<BetDTO> create(@PathVariable Long poolId, @RequestBody BetDTO obj){
        Bet bet = new Bet(obj);
        betService.addBetToPool(poolId, bet);
        BetDTO betDTO = new BetDTO(bet.getBetNumbers(), bet.getGameType());
        return ResponseEntity.status(HttpStatus.CREATED).body(betDTO);
    }

    @DeleteMapping(value = "/{betId}")
    public ResponseEntity<Void> remove(@PathVariable Long betId){
        betService.delete(betId);
        return ResponseEntity.noContent().build();
    }
}
