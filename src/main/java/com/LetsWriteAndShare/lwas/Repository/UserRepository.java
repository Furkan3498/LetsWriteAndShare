package com.LetsWriteAndShare.lwas.Repository;

import com.LetsWriteAndShare.lwas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , Long> {

    User findByEmail(String email);
    User findByActivationToken(String token);
}
