package com.ecommerce.repository;

import com.ecommerce.model.Product;

import java.util.List;
import java.util.Optional;

/**
 * Data access interface for Product entities.
 */
public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    List<Product> findByCategory(String categorySlug);
    void delete(Long id);
}
