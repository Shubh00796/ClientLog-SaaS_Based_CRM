package com.SaaS_Based_Customer_Relationship_Management.CRM.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "sales_data", indexes = {
        @Index(name = "idx_sales_customer", columnList = "customer_id"),
        @Index(name = "idx_sales_tenant", columnList = "tenant_id"),
        @Index(name = "idx_sales_product", columnList = "product_name")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "ProductName is required")
    @Column(name = "product_name", nullable = false)
    private String productName;

    @NotNull(message = "Quantity is required")
    @Column(nullable = false)
    private Integer quantity;

    @NotNull(message = "CustomerId is required")
    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @NotNull(message = "TenantId is required")
    @Column(name = "tenant_id", nullable = false)
    private Long tenantId;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
