package com.example.eCommerce_Backend.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    @JsonProperty("user_id")
    @Min(value = 1, message = "User id must greater than 1")
    private Long userId;
    @JsonProperty("fullname")
    private String fullName;

    private String email;

    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

//    @NotBlank(message = "Address is required")
    private String address;
    private String note;

    @JsonProperty("total_money")
    @Min(value = 0, message = "Total money must be >= 0")
    private Float totalMoney;

    @JsonProperty("shipping_method")
    private String shippingMethod;
    @JsonProperty("shipping_address")
    private String shippingAddress;

    @JsonProperty("payment_method")
    private String paymentMethod;
}
