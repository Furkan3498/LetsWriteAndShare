package com.LetsWriteAndShare.lwas.service;

import com.LetsWriteAndShare.lwas.Repository.UserRepository;
import com.LetsWriteAndShare.lwas.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public void save(User user){
      userRepository.save(user);
}}
