package com.SaaS_Based_Customer_Relationship_Management.CRM.controllers;


import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.*;
import com.SaaS_Based_Customer_Relationship_Management.CRM.entities.Customer;
import com.SaaS_Based_Customer_Relationship_Management.CRM.service_provider_interface.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing customers.
 */
@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Create a new customer.
     */
    @PostMapping
    public ResponseEntity<ApiResponse<CustomerResponseDTO>> createCustomer(
            @Valid @RequestBody CreateCustomerDTO dto) {
        CustomerResponseDTO created = customerService.createCustomer(dto);
        return ResponseEntity.ok(new ApiResponse<>(created, "Customer created successfully"));
    }

    /**
     * Get a customer by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponseDTO>> getCustomerById(@PathVariable Long id) {
        CustomerResponseDTO customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(new ApiResponse<>(customer, "Customer fetched successfully"));
    }

    /**
     * Get paginated customers by tenant ID.
     */
    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<ApiResponse<Page<CustomerResponseDTO>>> getCustomersByTenantId(
            @PathVariable Long tenantId,
            Pageable pageable) {
        Page<CustomerResponseDTO> customers = customerService.getCustomersByTenantId(tenantId, pageable);
        return ResponseEntity.ok(new ApiResponse<>(customers, "Customers fetched successfully"));
    }

    /**
     * Search customers by name and tenant ID.
     */
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<CustomerResponseDTO>>> searchCustomersByName(
            @RequestParam String name,
            @RequestParam Long tenantId) {
        List<CustomerResponseDTO> results = customerService.searchCustomersByName(name, tenantId);
        return ResponseEntity.ok(new ApiResponse<>(results, "Search completed successfully"));
    }

    /**
     * Update an existing customer.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponseDTO>> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCustomerDTO dto) {
        CustomerResponseDTO updated = customerService.updateCustomer(id, dto);
        return ResponseEntity.ok(new ApiResponse<>(updated, "Customer updated successfully"));
    }

    /**
     * Delete a customer by ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCustomer(Customer customer) {
        customerService.deleteCustomer(customer);
        return ResponseEntity.ok(new ApiResponse<>(null, "Customer deleted successfully"));
    }

    /**
     * Deactivate a customer (soft delete).
     */
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse<Void>> deactivateCustomer(@PathVariable Long id) {
        customerService.deactivateCustomer(id);
        return ResponseEntity.ok(new ApiResponse<>(null, "Customer deactivated successfully"));
    }

    /**
     * Check if a customer exists by email and tenant ID.
     */
    @GetMapping("/exists")
    public ResponseEntity<ApiResponse<Boolean>> existsByEmailAndTenant(
            @RequestParam String email,
            @RequestParam Long tenantId) {
        boolean exists = customerService.existsByEmailAndTenantId(email, tenantId);
        return ResponseEntity.ok(new ApiResponse<>(exists, "Existence check completed"));
    }
}
