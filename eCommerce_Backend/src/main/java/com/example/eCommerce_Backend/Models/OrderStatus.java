package com.example.eCommerce_Backend.Models;

//public class OrderStatus {
//    public static final String PENDING = "pending";
//    public static final String PROCESSING = "processing";
//    public static final String SHIPPED = "shipped";
//    public static final String DELIVERED = "delivered";
//    public static final String CANCELLED = "cancelled";
//}
public enum OrderStatus {
    PENDING("pending"),
    PROCESSING("processing"),
    SHIPPED("shipped"),
    DELIVERED("delivered"),
    CANCELLED("cancelled");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}