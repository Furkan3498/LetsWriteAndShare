package com.LetsWriteAndShare.lwas.service;


import com.LetsWriteAndShare.lwas.configuration.CurrentUser;
import com.LetsWriteAndShare.lwas.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AppUserDetailService implements UserDetailsService {

    private final UserService userService;

    public AppUserDetailService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User inDb = userService.findByUser(email);
        if (inDb == null){
            throw  new UsernameNotFoundException(email +" is not found");
        }
        return  new CurrentUser(inDb);
    }}
