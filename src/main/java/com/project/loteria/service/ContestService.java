package com.project.loteria.service;

import com.project.loteria.entities.Contest;
import com.project.loteria.repositories.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContestService {
    @Autowired
    private ContestRepository contestRepository;

    @Autowired
    private ResultService resultService;

    public Contest findById(Long id){
        Optional<Contest> contest = contestRepository.findById(id);
        return contest.orElseThrow(() -> new RuntimeException()); // criar excessão personalizada
    }

    public Contest insert(Contest obj){
        obj = contestRepository.save(obj);
        resultService.insertContest(obj);
        return obj;
    }
}
