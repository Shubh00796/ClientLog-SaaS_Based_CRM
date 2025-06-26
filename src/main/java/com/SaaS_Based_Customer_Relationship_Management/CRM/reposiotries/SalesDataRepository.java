package com.SaaS_Based_Customer_Relationship_Management.CRM.reposiotries;


import com.SaaS_Based_Customer_Relationship_Management.CRM.entities.SalesData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesDataRepository extends JpaRepository<SalesData, Long> {

    List<SalesData> findByTenantIdAndIsActiveTrue(Long tenantId);

    List<SalesData> findByProductNameContainingIgnoreCaseAndTenantIdAndIsActiveTrue(String productName, Long tenantId);

    List<SalesData> findByCustomerIdAndTenantIdAndIsActiveTrue(Long customerId, Long tenantId);
}
