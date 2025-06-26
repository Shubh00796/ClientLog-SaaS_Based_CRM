package com.SaaS_Based_Customer_Relationship_Management.CRM.service_provider_interface;


import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.CreateCustomerDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.CustomerResponseDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.UpdateCustomerDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {

    CustomerResponseDTO createCustomer(CreateCustomerDTO createCustomerDTO);

    CustomerResponseDTO getCustomerById(Long id);

    Page<CustomerResponseDTO> getCustomersByTenantId(Long tenantId, Pageable pageable);

    List<CustomerResponseDTO> searchCustomersByName(String name, Long tenantId);

    CustomerResponseDTO updateCustomer(Long id, UpdateCustomerDTO updateCustomerDTO);

    void deleteCustomer(Customer customer);

    void deactivateCustomer(Long id);

    boolean existsByEmailAndTenantId(String email, Long tenantId);
}

