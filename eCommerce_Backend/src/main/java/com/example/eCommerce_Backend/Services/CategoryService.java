package com.example.eCommerce_Backend.Services;


import com.example.eCommerce_Backend.DTOs.CategoryDTO;
import com.example.eCommerce_Backend.Models.Category;
import com.example.eCommerce_Backend.Repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{

    private final CategoryRepository categoryRepository;
    // Inject constructor
//    @Autowired
//    public CategoryService(CategoryRepository categoryRepository){
//        this.categoryRepository = categoryRepository;
//    }
//    Sử dụng @RequiredArgsConstructor để tựdđôg khởi tạo contructor cho trường

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        Category existCategory = categoryRepository.findByName(categoryDTO.getName())
                .orElse(null);
        if (existCategory == null){
            Category newCategory = Category.builder()
                    .name(categoryDTO.getName())
                    .build();
            return categoryRepository.save(newCategory);
        }else {
            throw new RuntimeException("Category is existed");
        }
    }

    @Override
    public Category getCategoryById(Long id) {
//        Optional<Category> category = categoryRepository.findById(id);
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Category id: %d not found", id)));
    }

    @Override
    public Page<Category> getAllCategories(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category updateCategory(Long id, CategoryDTO categoryDTO) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if(existingCategory.isPresent()){
            Category categoryUpdate = existingCategory.get();
            categoryUpdate.setName(categoryDTO.getName());
            return categoryUpdate;
        }else {
            throw new RuntimeException("Update failed because not find category");
        }
    }
    @Override
    // Xóa cứng
    public String deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return "Delete category successfully";
    }
}
