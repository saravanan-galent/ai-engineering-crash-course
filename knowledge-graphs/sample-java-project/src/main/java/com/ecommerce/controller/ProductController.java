package com.ecommerce.controller;

import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

/**
 * REST controller for product-related API endpoints.
 * Handles HTTP requests and delegates to ProductService.
 */
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public List<Product> getProducts(BigDecimal minPrice, BigDecimal maxPrice) {
        return productService.searchByPriceRange(minPrice, maxPrice);
    }

    public List<Product> getTopRated(int limit) {
        return productService.getTopRated(limit);
    }

    public void restockProduct(Long productId, int quantity) {
        productService.restockProduct(productId, quantity);
    }
}
