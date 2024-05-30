package com.example.eCommerce_Backend.Category;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    @GetMapping()
    public ResponseEntity<String> getAllCategories(){
        return ResponseEntity.status(HttpStatus.OK).body("hell");
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(
        @RequestParam(defaultValue = "-1" , required = false) int id,
        @RequestParam(defaultValue = "Invalid", required = false) String name
    )
    {
        return ResponseEntity.ok(String.format("id: %d, Name is: %s",id,name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable int id){
        return ResponseEntity.ok("update successfully id " + id);
    }

    @PostMapping()
    public ResponseEntity<?> insertCategory(
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
        return ResponseEntity.ok("insert success " + categoryDTO.toString());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int id){
        return ResponseEntity.ok("delete success id " + id);
    }
}
