package com.example.eCommerce_Backend.Repositories;

import com.example.eCommerce_Backend.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
