package com.example.eCommerce_Backend.Repositories;

import com.example.eCommerce_Backend.Models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
}
