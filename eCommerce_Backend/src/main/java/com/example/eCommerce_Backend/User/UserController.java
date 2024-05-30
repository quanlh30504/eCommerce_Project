package com.example.eCommerce_Backend.User;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/users")

public class UserController {

    @PostMapping("/register")
    public ResponseEntity<?> createUser(
        @Valid @RequestBody UserDTO userDTO,
        BindingResult result
    ){
        try {
            if (result.hasErrors())
            {
                List<String> errorMessage = result.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessage);
            }
            return ResponseEntity.ok("Register successfully");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
        @Valid @RequestBody UserLoginDTO userLoginDTO,
        BindingResult result
    ){
        try {
            if (result.hasErrors())
            {
                List<String> errorMessage = result.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessage);
            }
            return ResponseEntity.ok("Token is .......");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
