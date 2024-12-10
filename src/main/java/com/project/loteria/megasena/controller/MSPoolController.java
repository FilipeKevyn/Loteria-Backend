package com.project.loteria.megasena.controller;

import com.project.loteria.megasena.entities.MSPool;
import com.project.loteria.megasena.service.MSPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pool_megasena")
public class MSPoolController {
    @Autowired
    private MSPoolService service;

    @PostMapping("/creat")
    public ResponseEntity<Void> creatPool(){
        service.createPool();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
