package com.example.eCommerce_Backend.Repositories;

import com.example.eCommerce_Backend.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CategoryRepository extends JpaRepository<Category, Long> {


}
