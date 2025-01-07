package com.project.loteria.megasena.controller;

import com.project.loteria.megasena.dtos.MSContestDTO;
import com.project.loteria.entities.MSContest;
import com.project.loteria.megasena.service.MSContestService;
import com.project.loteria.megasena.service.MSResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/contest")
public class MSContestController {
    @Autowired
    private MSContestService contestService;

    @Autowired
    private MSResultService resultService;

    @GetMapping(value = "/{id}")
    private ResponseEntity<MSContest> findById(@PathVariable Long id){
        MSContest contest = contestService.findById(id);
        return ResponseEntity.ok().body(contest);
    }

    @PostMapping(value = "/{poolId}")
    public ResponseEntity<MSContest> addContest(@PathVariable Long poolId, @RequestBody MSContestDTO obj){
        MSContest contest = new MSContest(obj);
        contest = contestService.insert(contest);
        contestService.setContestInPool(poolId, contest);
        resultService.verifyAllBets(poolId);
        return ResponseEntity.status(HttpStatus.CREATED).body(contest);
    }
}
