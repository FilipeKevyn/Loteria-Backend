package com.project.loteria.controller;

import com.project.loteria.dtos.ContestDTO;
import com.project.loteria.entities.Contest;
import com.project.loteria.service.BetService;
import com.project.loteria.service.ContestService;
import com.project.loteria.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/contest")
public class ContestController {
    @Autowired
    private ContestService contestService;

    @Autowired
    private BetService betService;

    @Autowired
    private ResultService resultService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/{poolId}")
    public ResponseEntity<ContestDTO> create(@PathVariable Long poolId, @RequestBody ContestDTO obj){
        Contest contest = new Contest(obj);
        contestService.setContestInPool(poolId, contest);
        resultService.verifyAllBets(poolId);
        ContestDTO contestDTO = new ContestDTO(contest.getCodeContest(), contest.getDrawnNumbers());
        return ResponseEntity.status(HttpStatus.CREATED).body(contestDTO);
    }
}
