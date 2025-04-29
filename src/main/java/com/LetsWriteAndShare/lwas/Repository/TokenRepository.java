package com.LetsWriteAndShare.lwas.Repository;

import com.LetsWriteAndShare.lwas.auth.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TokenRepository extends JpaRepository<Token,String> {
}
