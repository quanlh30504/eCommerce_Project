package com.example.eCommerce_Backend.Services;

import com.example.eCommerce_Backend.DTOs.CategoryDTO;
import com.example.eCommerce_Backend.Models.Category;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CancellationException;

public interface ICategoryService {
    Category createCategory(CategoryDTO categoryDTO);
    Category getCategoryById(Long id);
    Page<Category> getAllCategories(int page, int size);
    Category updateCategory(Long id, CategoryDTO categoryDTO);
    String deleteCategory(Long id);
}
