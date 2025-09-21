package com.example.demo.dto.web;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.OrderDto;
import com.example.demo.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class WebDashboardDto {
    @JsonProperty("user")
    private UserDto user;
    
    @JsonProperty("orderHistory")
    private List<OrderDto> orderHistory;
    
    @JsonProperty("productCatalog")
    private List<ProductDto> productCatalog;
    
    @JsonProperty("analytics")
    private AnalyticsDto analytics;
    
    @JsonProperty("pagination")
    private PaginationDto pagination;

    // Constructors
    public WebDashboardDto() {}

    public WebDashboardDto(UserDto user, List<OrderDto> orderHistory, 
                          List<ProductDto> productCatalog, AnalyticsDto analytics, PaginationDto pagination) {
        this.user = user;
        this.orderHistory = orderHistory;
        this.productCatalog = productCatalog;
        this.analytics = analytics;
        this.pagination = pagination;
    }

    // Getters and Setters
    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<OrderDto> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<OrderDto> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public List<ProductDto> getProductCatalog() {
        return productCatalog;
    }

    public void setProductCatalog(List<ProductDto> productCatalog) {
        this.productCatalog = productCatalog;
    }

    public AnalyticsDto getAnalytics() {
        return analytics;
    }

    public void setAnalytics(AnalyticsDto analytics) {
        this.analytics = analytics;
    }

    public PaginationDto getPagination() {
        return pagination;
    }

    public void setPagination(PaginationDto pagination) {
        this.pagination = pagination;
    }

    public static class AnalyticsDto {
        @JsonProperty("totalOrders")
        private Long totalOrders;
        
        @JsonProperty("totalSpent")
        private Double totalSpent;
        
        @JsonProperty("categoriesStats")
        private Map<String, Integer> categoriesStats;

        public AnalyticsDto() {}

        public AnalyticsDto(Long totalOrders, Double totalSpent, Map<String, Integer> categoriesStats) {
            this.totalOrders = totalOrders;
            this.totalSpent = totalSpent;
            this.categoriesStats = categoriesStats;
        }

        // Getters and Setters
        public Long getTotalOrders() {
            return totalOrders;
        }

        public void setTotalOrders(Long totalOrders) {
            this.totalOrders = totalOrders;
        }

        public Double getTotalSpent() {
            return totalSpent;
        }

        public void setTotalSpent(Double totalSpent) {
            this.totalSpent = totalSpent;
        }

        public Map<String, Integer> getCategoriesStats() {
            return categoriesStats;
        }

        public void setCategoriesStats(Map<String, Integer> categoriesStats) {
            this.categoriesStats = categoriesStats;
        }
    }

    public static class PaginationDto {
        @JsonProperty("currentPage")
        private Integer currentPage;
        
        @JsonProperty("totalPages")
        private Integer totalPages;
        
        @JsonProperty("pageSize")
        private Integer pageSize;
        
        @JsonProperty("totalElements")
        private Long totalElements;

        public PaginationDto() {}

        public PaginationDto(Integer currentPage, Integer totalPages, Integer pageSize, Long totalElements) {
            this.currentPage = currentPage;
            this.totalPages = totalPages;
            this.pageSize = pageSize;
            this.totalElements = totalElements;
        }

        // Getters and Setters
        public Integer getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(Integer currentPage) {
            this.currentPage = currentPage;
        }

        public Integer getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(Integer totalPages) {
            this.totalPages = totalPages;
        }

        public Integer getPageSize() {
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        public Long getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(Long totalElements) {
            this.totalElements = totalElements;
        }
    }
}
