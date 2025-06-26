package com.SaaS_Based_Customer_Relationship_Management.CRM.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesDataResponseDTO {

    private Long id;
    private String productName;
    private Integer quantity;
    private Long customerId;
    private Long tenantId;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
