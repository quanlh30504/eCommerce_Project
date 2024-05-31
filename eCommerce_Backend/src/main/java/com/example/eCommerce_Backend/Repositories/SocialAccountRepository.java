package com.example.eCommerce_Backend.Repositories;

import com.example.eCommerce_Backend.Models.SocialAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialAccountRepository extends JpaRepository<SocialAccount, Long> {
}
