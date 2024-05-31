package com.example.eCommerce_Backend.Repositories;

import com.example.eCommerce_Backend.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
