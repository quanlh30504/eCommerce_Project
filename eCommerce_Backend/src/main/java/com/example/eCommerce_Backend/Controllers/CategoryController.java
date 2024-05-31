package com.example.eCommerce_Backend.Controllers;

import com.example.eCommerce_Backend.DTOs.CategoryDTO;
import com.example.eCommerce_Backend.Models.Category;
import com.example.eCommerce_Backend.Services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/categories")
// Dependency injection
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<?> getAllCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ){
        return ResponseEntity.status(HttpStatus.OK).body(
                categoryService.getAllCategories(page,size)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(
            @PathVariable Long id
    )
    {
        try {
            return ResponseEntity.ok(categoryService.getCategoryById(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryDTO categoryDTO,
            BindingResult result
    ){
        if(result.hasErrors()){
            List<String> errorMessage = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessage);
        }
        try {
            Category updatedCategory = categoryService.updateCategory(id, categoryDTO);
            return ResponseEntity.ok("update successfully category id " + id);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<?> createCategory(
            @Valid @RequestBody CategoryDTO categoryDTO,
            BindingResult result
    )
    {
        if(result.hasErrors()){
            List<String> errorMessage = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessage);
        }
        try {
            Category category = categoryService.createCategory(categoryDTO);
            return ResponseEntity.ok("insert success \n" + category.toString());
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int id){
        return ResponseEntity.ok("delete success id " + id);
    }
}
