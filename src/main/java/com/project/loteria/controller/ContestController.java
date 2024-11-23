package com.project.loteria.controller;

import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Contest;
import com.project.loteria.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/contest")
public class ContestController {
    @Autowired
    private ContestService contestService;

    @GetMapping(value = "/{id}")
    private ResponseEntity<Contest> findById(@PathVariable Long id){
        Contest contest = contestService.findById(id);
        return ResponseEntity.ok().body(contest);
    }

    @PostMapping
    public ResponseEntity<Contest> insert(@RequestBody Contest contest){
        contest = contestService.insert(contest);
        return ResponseEntity.ok().body(contest);
    }
}
