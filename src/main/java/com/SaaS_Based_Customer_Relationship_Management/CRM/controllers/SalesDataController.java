package com.SaaS_Based_Customer_Relationship_Management.CRM.controllers;

import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.ApiResponse;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.CreateSalesDataDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.SalesDataResponseDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.UpdateSalesDataDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.entities.SalesData;
import com.SaaS_Based_Customer_Relationship_Management.CRM.service_provider_interface.SalesDataService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sales-data")
@RequiredArgsConstructor
public class SalesDataController {

    private final SalesDataService salesDataService;

    /**
     * Create new sales data.
     */
    @PostMapping
    public ResponseEntity<ApiResponse<SalesDataResponseDTO>> createSalesData(
            @Valid @RequestBody CreateSalesDataDTO dto) {
        SalesDataResponseDTO created = salesDataService.createSalesData(dto);
        return ResponseEntity.ok(new ApiResponse<>(created, "Sales data created successfully", LocalDateTime.now()));
    }

    /**
     * Get sales data by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SalesDataResponseDTO>> getSalesDataById(@PathVariable Long id) {
        SalesDataResponseDTO data = salesDataService.getSalesDataById(id);
        return ResponseEntity.ok(new ApiResponse<>(data, "Sales data fetched successfully", LocalDateTime.now()));
    }

    /**
     * Get paginated sales data by tenant ID.
     */
    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<ApiResponse<Page<SalesDataResponseDTO>>> getSalesDataByTenantId(
            @PathVariable Long tenantId,
            Pageable pageable) {
        Page<SalesDataResponseDTO> page = salesDataService.getSalesDataByTenantId(tenantId, pageable);
        return ResponseEntity.ok(new ApiResponse<>(page, "Sales data fetched successfully", LocalDateTime.now()));
    }

    /**
     * Search sales data by product name and tenant ID.
     */
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<SalesDataResponseDTO>>> searchSalesDataByProductName(
            @RequestParam String productName,
            @RequestParam Long tenantId) {
        List<SalesDataResponseDTO> results = salesDataService.searchSalesDataByProductName(productName, tenantId);
        return ResponseEntity.ok(new ApiResponse<>(results, "Search completed successfully", LocalDateTime.now()));
    }

    /**
     * Get sales data by customer ID and tenant ID.
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<List<SalesDataResponseDTO>>> getSalesDataByCustomerId(
            @PathVariable Long customerId,
            @RequestParam Long tenantId) {
        List<SalesDataResponseDTO> results = salesDataService.getSalesDataByCustomerId(customerId, tenantId);
        return ResponseEntity.ok(new ApiResponse<>(results, "Sales data fetched successfully", LocalDateTime.now()));
    }

    /**
     * Update existing sales data.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SalesDataResponseDTO>> updateSalesData(
            @PathVariable Long id,
            @Valid @RequestBody UpdateSalesDataDTO dto) {
        SalesDataResponseDTO updated = salesDataService.updateSalesData(id, dto);
        return ResponseEntity.ok(new ApiResponse<>(updated, "Sales data updated successfully", LocalDateTime.now()));
    }

    /**
     * Delete sales data by ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSalesData(SalesData salesData) {
        salesDataService.deleteSalesData(salesData);
        return ResponseEntity.ok(new ApiResponse<>(null, "Sales data deleted successfully", LocalDateTime.now()));
    }

    /**
     * Deactivate sales data (soft delete).
     */
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse<Void>> deactivateSalesData(@PathVariable Long id) {
        salesDataService.deactivateSalesData(id);
        return ResponseEntity.ok(new ApiResponse<>(null, "Sales data deactivated successfully", LocalDateTime.now()));
    }
}
