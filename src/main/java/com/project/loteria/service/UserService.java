package com.project.loteria.service;

import com.project.loteria.dtos.PoolDTO;
import com.project.loteria.entities.Pool;
import com.project.loteria.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
//    @Autowired
//    private UserRepository repository;
//    @Autowired
//    private PoolService poolService;
//
//    // pseudo métodos
//
//    public void createPool(UUID id, PoolDTO poolDTO){
//        Pool pool = poolService.createPool(poolDTO);
//        User user = repository.findById(id).orElseThrow(() -> new RuntimeException());
//        user.getPools().add(pool);
//    }
//
//    public void addFriend(UUID id, UUID idFriend){
//        User user = repository.findById(id).orElseThrow(() -> new RuntimeException());
//        User friend = repository.findById(idFriend).orElseThrow(() -> new RuntimeException());
//
//        user.getFriends().add(friend);
//        friend.getFriends().add(user);
//    }
}
