package com.example.demo.dto.mobile;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.OrderDto;
import com.example.demo.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class MobileDashboardDto {
    @JsonProperty("user")
    private UserDto user;
    
    @JsonProperty("recentOrders")
    private List<OrderDto> recentOrders;
    
    @JsonProperty("recommendedProducts")
    private List<ProductDto> recommendedProducts;
    
    @JsonProperty("notifications")
    private List<NotificationDto> notifications;

    // Constructors
    public MobileDashboardDto() {}

    public MobileDashboardDto(UserDto user, List<OrderDto> recentOrders, 
                             List<ProductDto> recommendedProducts, List<NotificationDto> notifications) {
        this.user = user;
        this.recentOrders = recentOrders;
        this.recommendedProducts = recommendedProducts;
        this.notifications = notifications;
    }

    // Getters and Setters
    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<OrderDto> getRecentOrders() {
        return recentOrders;
    }

    public void setRecentOrders(List<OrderDto> recentOrders) {
        this.recentOrders = recentOrders;
    }

    public List<ProductDto> getRecommendedProducts() {
        return recommendedProducts;
    }

    public void setRecommendedProducts(List<ProductDto> recommendedProducts) {
        this.recommendedProducts = recommendedProducts;
    }

    public List<NotificationDto> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationDto> notifications) {
        this.notifications = notifications;
    }

    public static class NotificationDto {
        @JsonProperty("id")
        private String id;
        
        @JsonProperty("title")
        private String title;
        
        @JsonProperty("message")
        private String message;
        
        @JsonProperty("type")
        private String type;

        public NotificationDto() {}

        public NotificationDto(String id, String title, String message, String type) {
            this.id = id;
            this.title = title;
            this.message = message;
            this.type = type;
        }

        // Getters and Setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
