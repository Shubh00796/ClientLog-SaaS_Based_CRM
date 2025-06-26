package com.SaaS_Based_Customer_Relationship_Management.CRM.service_provider_interface;


import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.CreateSalesDataDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.SalesDataResponseDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.UpdateSalesDataDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SalesDataService {

    SalesDataResponseDTO createSalesData(CreateSalesDataDTO createSalesDataDTO);

    SalesDataResponseDTO getSalesDataById(Long id);

    Page<SalesDataResponseDTO> getSalesDataByTenantId(Long tenantId, Pageable pageable);

    List<SalesDataResponseDTO> searchSalesDataByProductName(String productName, Long tenantId);

    List<SalesDataResponseDTO> getSalesDataByCustomerId(Long customerId, Long tenantId);

    SalesDataResponseDTO updateSalesData(Long id, UpdateSalesDataDTO updateSalesDataDTO);

    void deleteSalesData(Long id);

    void deactivateSalesData(Long id);
}
