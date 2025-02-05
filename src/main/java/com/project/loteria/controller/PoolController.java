package com.project.loteria.controller;

import com.project.loteria.dtos.BetDTO;
import com.project.loteria.dtos.PoolDTO;
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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/{poolId}/bets")
    public List<BetDTO> findBetsByPoolId(@PathVariable Long poolId){
        return service.getAllBets(service.findById(poolId))
                .stream().map(e -> new BetDTO(e.getBetNumbers(), e.getGameType()))
                .toList();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/{poolId}")
    public ResponseEntity<Pool> findPoolById(@PathVariable Long poolId) {
        return ResponseEntity.ok().body(service.findById(poolId));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<Pool>> getAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @DeleteMapping(value = "/{poolId}")
    public ResponseEntity<Void> remove(@PathVariable Long poolId){
        service.deletePool(poolId);
        return ResponseEntity.noContent().build();
    }
}
