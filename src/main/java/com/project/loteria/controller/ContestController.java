package com.project.loteria.controller;

import com.project.loteria.entities.Contest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/contest")
public class ContestController {
    @Autowired
    private ContestService contestService;

    @PostMapping
    public ResponseEntity<Contest> insert(@RequestBody Contest contest){
        contest = contestService.insert(contest);
        return ResponseEntity.ok().body(contest);
    }
}
