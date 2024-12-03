package com.project.loteria.megasena.service;

import com.project.loteria.interfaces.ContestService;
import com.project.loteria.megasena.entities.MSContest;
import com.project.loteria.megasena.repositories.MSContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MSContestService implements ContestService<MSContest> {
    @Autowired
    private MSContestRepository contestRepository;

    @Autowired
    private MSResultService resultService;

    public MSContest findById(Long id){
        Optional<MSContest> contest = contestRepository.findById(id);
        return contest.orElseThrow(() -> new RuntimeException()); // criar excessão personalizada
    }

    public MSContest insert(MSContest obj){
        obj = contestRepository.save(obj);
        resultService.insertContest(obj);
        return obj;
    }
}
