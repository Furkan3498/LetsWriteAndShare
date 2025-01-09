package com.LetsWriteAndShare.lwas.service;

import com.LetsWriteAndShare.lwas.Repository.UserRepository;
import com.LetsWriteAndShare.lwas.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void save(User user){
      userRepository.save(user);
}}
