package com.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a product category with hierarchical support.
 * Categories can have a parent category for nested taxonomies.
 */
public class Category extends BaseEntity {

    private String name;
    private String slug;
    private Category parentCategory;
    private List<Product> products;

    public Category(String name) {
        this.name = name;
        this.slug = name.toLowerCase().replaceAll("\\s+", "-");
        this.products = new ArrayList<>();
    }

    public boolean isRootCategory() {
        return parentCategory == null;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public String getName() { return name; }
    public String getSlug() { return slug; }
    public Category getParentCategory() { return parentCategory; }
    public void setParentCategory(Category parent) { this.parentCategory = parent; }
    public List<Product> getProducts() { return products; }
}
