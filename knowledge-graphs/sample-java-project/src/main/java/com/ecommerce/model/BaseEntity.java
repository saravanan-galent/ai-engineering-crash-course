package com.ecommerce.model;

import java.time.LocalDateTime;

/**
 * Base class for all persistent entities.
 * Provides common fields like id, createdAt, updatedAt.
 */
public abstract class BaseEntity {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BaseEntity() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void markUpdated() {
        this.updatedAt = LocalDateTime.now();
    }
}
