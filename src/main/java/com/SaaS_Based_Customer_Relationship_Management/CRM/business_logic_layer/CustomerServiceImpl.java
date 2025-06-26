package com.SaaS_Based_Customer_Relationship_Management.CRM.business_logic_layer;

import com.SaaS_Based_Customer_Relationship_Management.CRM.data_access_layer.CustomerRepoService;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.CreateCustomerDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.CustomerResponseDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.UpdateCustomerDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.entities.Customer;
import com.SaaS_Based_Customer_Relationship_Management.CRM.mappers.CustomerMapper;
import com.SaaS_Based_Customer_Relationship_Management.CRM.service_provider_interface.CustomerService;
import com.SaaS_Based_Customer_Relationship_Management.CRM.valadations_utils.CustomerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepoService repoService;
    private final CustomerMapper mapper;
    private final CustomerValidator customerValidator;

    @Override
    @Transactional
    public CustomerResponseDTO createCustomer(CreateCustomerDTO createCustomerDTO) {
        customerValidator.validateCreateRequest(createCustomerDTO);
        Customer customer = mapper.toEntity(createCustomerDTO);
        customer.setCreatedAt(LocalDateTime.now());

        return mapper.toDto(repoService.save(customer));
    }

    @Override
    public CustomerResponseDTO getCustomerById(Long id) {
        return null;
    }

    @Override
    public Page<CustomerResponseDTO> getCustomersByTenantId(Long tenantId, Pageable pageable) {
        return null;
    }

    @Override
    public List<CustomerResponseDTO> searchCustomersByName(String name, Long tenantId) {
        return List.of();
    }

    @Override
    public CustomerResponseDTO updateCustomer(Long id, UpdateCustomerDTO updateCustomerDTO) {
        return null;
    }

    @Override
    public void deleteCustomer(Long id) {

    }

    @Override
    public void deactivateCustomer(Long id) {

    }

    @Override
    public boolean existsByEmailAndTenantId(String email, Long tenantId) {
        return false;
    }
}
