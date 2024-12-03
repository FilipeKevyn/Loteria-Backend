package com.project.loteria.megasena.controller;

import com.project.loteria.megasena.entities.MSContest;
import com.project.loteria.megasena.service.MSContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/contest")
public class MSContestController {
    @Autowired
    private MSContestService contestService;

    @GetMapping(value = "/{id}")
    private ResponseEntity<MSContest> findById(@PathVariable Long id){
        MSContest contest = contestService.findById(id);
        return ResponseEntity.ok().body(contest);
    }

    @PostMapping
    public ResponseEntity<MSContest> insert(@RequestBody MSContest contest){
        contest = contestService.insert(contest);
        return ResponseEntity.ok().body(contest);
    }
}
