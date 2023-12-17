package com.erpak.barter.repository;

import com.erpak.barter.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
      select t from Token t inner join User u\s
      on t.user.id = u.id\s
      where u.id = :userId and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokensByUser(Integer userId);

    Optional<Token> findByToken(String token);
}
