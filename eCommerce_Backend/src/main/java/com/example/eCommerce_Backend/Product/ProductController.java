package com.example.eCommerce_backend.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

import ch.qos.logback.core.util.StringUtil;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @GetMapping()
    public ResponseEntity<String> getFullProducts(){
        return ResponseEntity.ok("get full product ok");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(@PathVariable int id){
        return ResponseEntity.ok("Product id " + id);
    }

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> insertProduct(
            @Valid @ModelAttribute ProductDTO productDTO,
            BindingResult result
    ){
        try{
            if (result.hasErrors())
            {
                List<String> errorMessage = result.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessage);
            }
            // Upload nhiều file ảnh
            List<MultipartFile> files = productDTO.getFiles();
            // Debug: files null sẽ ném ngoại lệ nullpoiter -> tạo 1 list rỗng
            files = files == null ? new ArrayList<MultipartFile>() : files;
            // Kiểm tra từng file
            for (MultipartFile file : files){
                // Bug: có biến file nhưng ko có file -> size = 0
                if (file.getSize() == 0) {
                    continue;
                }
                // Kiểm tra file nếu tồn tại
                if (file != null) {
                    //Kiểm tra kích thước vả định dạng file
                    if (file.getSize() > 10 * 1024 * 1024){ // Kích thước > 10MB
                        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(
                                "File is too large! Maximum size is 10MB"
                        );
                    }
                    // Kiểm tra định dạng file
                    String contentType = file.getContentType();
                    if (contentType == null || !contentType.startsWith("image/")) {
                        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(
                                "File must be an image"
                        );
                    }
                }
                // Lưu file và cập nhật thumbnail trong DTO
                String filename = storeFile(file);
                // Thêm vào db
            }
            return ResponseEntity.ok("created successfully " + productDTO.toString());
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // Xử lý logic lưu file
    private String storeFile(MultipartFile file) throws IOException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        // Bug: upload file có tên giống nhau nhưng nội dung khác nhau -> Bị ghi đề , mất file
        // Solution: Thêm UUID vào trước tên file để đảm bảo tên file là duy nhất
        String uniqueFilename = UUID.randomUUID().toString() + "_" + filename;
        // Đường dẫn đến thư mục lưu file
        java.nio.file.Path uploadDir = Paths.get("uploads");
        // Kiểm tra vào tạo thư mục nếu nó không tồn tại
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }
        // Đường dẫn đầy đủ đến file
        java.nio.file.Path destination = Paths.get(uploadDir.toString(), uniqueFilename);
        // Sao chép file vào thư mục đích
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

        return uniqueFilename;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id){
        return ResponseEntity.ok("Updated successfully product id: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        return ResponseEntity.ok("Deleted successfully product id: " + id);
    }
}
