package com.SaaS_Based_Customer_Relationship_Management.CRM.data_access_layer;

import com.SaaS_Based_Customer_Relationship_Management.CRM.entities.SalesData;
import com.SaaS_Based_Customer_Relationship_Management.CRM.exceptions.ResourceNotFoundException;
import com.SaaS_Based_Customer_Relationship_Management.CRM.reposiotries.SalesDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesDataRepoService {

    private final SalesDataRepository salesDataRepository;

    public SalesData findById(Long id) {
        return salesDataRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sales data not found with ID: " + id));
    }

    public List<SalesData> findByTenantId(Long tenantId) {
        return salesDataRepository.findByTenantIdAndIsActiveTrue(tenantId);
    }

    public List<SalesData> searchByProductName(String productName, Long tenantId) {
        return salesDataRepository.findByProductNameContainingIgnoreCaseAndTenantIdAndIsActiveTrue(productName, tenantId);
    }

    public List<SalesData> findByCustomerId(Long customerId, Long tenantId) {
        return salesDataRepository.findByCustomerIdAndTenantIdAndIsActiveTrue(customerId, tenantId);
    }

    public SalesData save(SalesData salesData) {
        return salesDataRepository.save(salesData);
    }

    public SalesData update(SalesData salesData) {
        return salesDataRepository.save(salesData);
    }

    public void delete(SalesData salesData) {
        salesDataRepository.delete(salesData);
    }

    public List<SalesData> findAll() {
        return salesDataRepository.findAll();
    }
}
