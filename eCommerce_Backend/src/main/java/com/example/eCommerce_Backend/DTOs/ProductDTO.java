package com.example.eCommerce_Backend.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    @NotEmpty(message = "Product's name can not empty")
    @Size(min=3, max=200, message = "Title must be between 3 and 200 characters")
    private String name;

    @Min(value = 0, message = "Price must be greater than or equal to 0")
    @Max(value = 10000000, message = "Price must be less than or equal to 10,000,000")
    private Double price;

    private String thumbnail;
    private String description;

    @JsonProperty("category_id")
    private String categoryId;
    private List<MultipartFile> files;

}
