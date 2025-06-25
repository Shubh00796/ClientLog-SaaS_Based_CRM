package com.SaaS_Based_Customer_Relationship_Management.CRM.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponseDTO {

    private Long id;
    private String name;
    private String email;
    private Long tenantId;
}
